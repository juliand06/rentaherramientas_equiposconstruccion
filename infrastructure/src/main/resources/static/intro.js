/**
 * RENTATOOLS PRO — INTRO & ARTEFACTO MECÁNICO INTERACTIVO
 * Maneja cinemática reactiva, partículas en canvas, sintetizador Web Audio y transición cinemática.
 */

(function () {
    'use strict';

    // Elementos del DOM
    const introScreen = document.getElementById('intro-screen');
    const introCanvas = document.getElementById('intro-canvas');
    const artifactTarget = document.getElementById('artifact-target');
    const btnEnter = document.getElementById('btn-enter-platform');
    const btnSkip = document.getElementById('btn-skip-intro');
    const btnSoundToggle = document.getElementById('btn-sound-toggle');
    const soundIcon = document.getElementById('sound-icon');
    const btnReplayIntro = document.getElementById('btn-replay-intro');
    const mouseGlow = document.getElementById('mouse-glow');
    const hudPressure = document.getElementById('hud-pressure');
    const doorLeft = document.getElementById('door-left');
    const doorRight = document.getElementById('door-right');

    const armBoom = document.getElementById('arm-boom');
    const armStick = document.getElementById('arm-stick');
    const sparkPoint = document.getElementById('impact-spark-point');

    // Contexto de Canvas
    let ctx = null;
    let canvasW = 0;
    let canvasH = 0;
    let particles = [];
    let ambientParticles = [];
    let isLaunching = false;
    let soundEnabled = true;

    // Web Audio API
    let audioCtx = null;

    function getAudioContext() {
        if (!audioCtx) {
            const AudioContextClass = window.AudioContext || window.webkitAudioContext;
            if (AudioContextClass) {
                audioCtx = new AudioContextClass();
            }
        }
        if (audioCtx && audioCtx.state === 'suspended') {
            audioCtx.resume();
        }
        return audioCtx;
    }

    // Efectos de Sonido Sintetizados (Sin depender de archivos externos)
    function playHoverSound() {
        if (!soundEnabled) return;
        try {
            const actx = getAudioContext();
            if (!actx) return;
            const osc = actx.createOscillator();
            const gain = actx.createGain();
            osc.type = 'sawtooth';
            osc.frequency.setValueAtTime(85, actx.currentTime);
            osc.frequency.exponentialRampToValueAtTime(110, actx.currentTime + 0.12);

            const filter = actx.createBiquadFilter();
            filter.type = 'lowpass';
            filter.frequency.setValueAtTime(320, actx.currentTime);

            gain.gain.setValueAtTime(0.04, actx.currentTime);
            gain.gain.exponentialRampToValueAtTime(0.001, actx.currentTime + 0.15);

            osc.connect(filter);
            filter.connect(gain);
            gain.connect(actx.destination);

            osc.start();
            osc.stop(actx.currentTime + 0.15);
        } catch (e) {
            // Audio no permitido o silenciado
        }
    }

    function playIgnitionSequence() {
        if (!soundEnabled) return;
        try {
            const actx = getAudioContext();
            if (!actx) return;
            const now = actx.currentTime;

            // 1. Clic Mecánico Metálico
            const clickOsc = actx.createOscillator();
            const clickGain = actx.createGain();
            clickOsc.type = 'triangle';
            clickOsc.frequency.setValueAtTime(800, now);
            clickOsc.frequency.exponentialRampToValueAtTime(120, now + 0.05);
            clickGain.gain.setValueAtTime(0.3, now);
            clickGain.gain.exponentialRampToValueAtTime(0.001, now + 0.05);
            clickOsc.connect(clickGain);
            clickGain.connect(actx.destination);
            clickOsc.start(now);
            clickOsc.stop(now + 0.05);

            // 2. Escape Hidráulico (Ruido blanco filtrado)
            const bufferSize = actx.sampleRate * 0.4;
            const buffer = actx.createBuffer(1, bufferSize, actx.sampleRate);
            const data = buffer.getChannelData(0);
            for (let i = 0; i < bufferSize; i++) {
                data[i] = Math.random() * 2 - 1;
            }
            const noise = actx.createBufferSource();
            noise.buffer = buffer;

            const noiseFilter = actx.createBiquadFilter();
            noiseFilter.type = 'bandpass';
            noiseFilter.frequency.setValueAtTime(1800, now + 0.04);
            noiseFilter.Q.setValueAtTime(3, now + 0.04);

            const noiseGain = actx.createGain();
            noiseGain.gain.setValueAtTime(0.18, now + 0.04);
            noiseGain.gain.exponentialRampToValueAtTime(0.001, now + 0.45);

            noise.connect(noiseFilter);
            noiseFilter.connect(noiseGain);
            noiseGain.connect(actx.destination);
            noise.start(now + 0.04);
            noise.stop(now + 0.45);

            // 3. Encendido de Motor Pesado / Bajo Profundo
            const bassOsc = actx.createOscillator();
            const bassGain = actx.createGain();
            bassOsc.type = 'sawtooth';
            bassOsc.frequency.setValueAtTime(45, now + 0.1);
            bassOsc.frequency.exponentialRampToValueAtTime(140, now + 0.7);

            const bassFilter = actx.createBiquadFilter();
            bassFilter.type = 'lowpass';
            bassFilter.frequency.setValueAtTime(250, now + 0.1);
            bassFilter.frequency.exponentialRampToValueAtTime(600, now + 0.8);

            bassGain.gain.setValueAtTime(0.01, now + 0.1);
            bassGain.gain.linearRampToValueAtTime(0.28, now + 0.3);
            bassGain.gain.exponentialRampToValueAtTime(0.001, now + 0.85);

            bassOsc.connect(bassFilter);
            bassFilter.connect(bassGain);
            bassGain.connect(actx.destination);
            bassOsc.start(now + 0.1);
            bassOsc.stop(now + 0.85);

        } catch (e) {
            // Audio error ignorado
        }
    }

    // Configuración del Canvas de Partículas
    function initCanvas() {
        if (!introCanvas) return;
        ctx = introCanvas.getContext('2d');
        resizeCanvas();
        window.addEventListener('resize', resizeCanvas);

        // Crear partículas ambientales iniciales
        ambientParticles = [];
        for (let i = 0; i < 45; i++) {
            ambientParticles.push({
                x: Math.random() * canvasW,
                y: Math.random() * canvasH,
                size: Math.random() * 2.2 + 0.8,
                speedY: -(Math.random() * 0.6 + 0.2),
                speedX: (Math.random() - 0.5) * 0.4,
                opacity: Math.random() * 0.5 + 0.2,
                gold: Math.random() > 0.4
            });
        }

        requestAnimationFrame(renderLoop);
    }

    function resizeCanvas() {
        if (!introCanvas) return;
        canvasW = introCanvas.width = window.innerWidth;
        canvasH = introCanvas.height = window.innerHeight;
    }

    // Emisión de chispas en el punto de contacto del artefacto
    function emitSparks(originX, originY, count, speedMultiplier = 1) {
        for (let i = 0; i < count; i++) {
            const angle = (Math.random() * Math.PI) + Math.PI; // hacia arriba y lados
            const speed = (Math.random() * 6 + 2) * speedMultiplier;
            particles.push({
                x: originX,
                y: originY,
                vx: Math.cos(angle) * speed + (Math.random() - 0.5) * 2,
                vy: Math.sin(angle) * speed - 2,
                size: Math.random() * 3 + 1.2,
                life: 1.0,
                decay: Math.random() * 0.03 + 0.015,
                color: Math.random() > 0.3 ? '#fbbf24' : '#ffffff'
            });
        }
    }

    // Loop de renderizado 60 FPS
    function renderLoop() {
        if (!ctx) return;
        ctx.clearRect(0, 0, canvasW, canvasH);

        // Renderizar partículas ambientales de polvo iluminado
        ctx.save();
        for (let i = 0; i < ambientParticles.length; i++) {
            const p = ambientParticles[i];
            p.y += p.speedY;
            p.x += p.speedX;

            if (p.y < 0) {
                p.y = canvasH + 10;
                p.x = Math.random() * canvasW;
            }

            ctx.beginPath();
            ctx.arc(p.x, p.y, p.size, 0, Math.PI * 2);
            ctx.fillStyle = p.gold
                ? `rgba(245, 158, 11, ${p.opacity})`
                : `rgba(56, 189, 248, ${p.opacity * 0.7})`;
            ctx.fill();
        }
        ctx.restore();

        // Renderizar chispas activas
        ctx.save();
        for (let i = particles.length - 1; i >= 0; i--) {
            const sp = particles[i];
            sp.x += sp.vx;
            sp.y += sp.vy;
            sp.vy += 0.22; // gravedad
            sp.life -= sp.decay;

            if (sp.life <= 0) {
                particles.splice(i, 1);
                continue;
            }

            ctx.beginPath();
            ctx.arc(sp.x, sp.y, sp.size * sp.life, 0, Math.PI * 2);
            ctx.fillStyle = sp.color;
            ctx.shadowBlur = 8;
            ctx.shadowColor = '#f59e0b';
            ctx.fill();
        }
        ctx.restore();

        // Emisión continua suave desde el percutor
        if (sparkPoint && !isLaunching && Math.random() > 0.4) {
            const rect = sparkPoint.getBoundingClientRect();
            emitSparks(rect.left + rect.width / 2, rect.top + rect.height / 2, 2, 0.7);
        }

        requestAnimationFrame(renderLoop);
    }

    // Cinemática Reactiva al Mover el Mouse
    function setupParallaxInteractions() {
        if (!artifactTarget) return;

        window.addEventListener('mousemove', (e) => {
            if (isLaunching) return;

            const rect = artifactTarget.getBoundingClientRect();
            const centerX = rect.left + rect.width / 2;
            const centerY = rect.top + rect.height / 2;

            // Offset normalizado (-1 a 1)
            const deltaX = (e.clientX - centerX) / (window.innerWidth / 2);
            const deltaY = (e.clientY - centerY) / (window.innerHeight / 2);

            // Posicionar el glow interactivo
            if (mouseGlow) {
                const localX = e.clientX - rect.left;
                const localY = e.clientY - rect.top;
                mouseGlow.style.left = `${localX}px`;
                mouseGlow.style.top = `${localY}px`;
            }

            // Rotación suave del brazo hidráulico (Boom & Stick)
            if (armBoom) {
                const boomAngle = deltaY * 8 - deltaX * 4;
                armBoom.style.transform = `rotate(${boomAngle}deg)`;
            }

            if (armStick) {
                const stickAngle = -deltaY * 12 + deltaX * 6;
                armStick.style.transform = `rotate(${stickAngle}deg)`;
            }
        });

        // Sonido y chispas al posar el mouse sobre la máquina
        artifactTarget.addEventListener('mouseenter', () => {
            playHoverSound();
            if (sparkPoint) {
                const rect = sparkPoint.getBoundingClientRect();
                emitSparks(rect.left + rect.width / 2, rect.top + rect.height / 2, 14, 1.2);
            }
        });

        // Simular jitter dinámico de presión hidráulica
        setInterval(() => {
            if (hudPressure) {
                const base = 380;
                const jitter = Math.floor(Math.random() * 18);
                hudPressure.textContent = `${base + jitter} BAR`;
            }
        }, 1200);
    }

    // Transición Cinemática de Entrada
    function triggerEnterSequence() {
        if (isLaunching) return;
        isLaunching = true;

        // Sonido de ignición
        playIgnitionSequence();

        // Explosión de chispas desde el punto de impacto
        if (sparkPoint) {
            const rect = sparkPoint.getBoundingClientRect();
            emitSparks(rect.left + rect.width / 2, rect.top + rect.height / 2, 110, 2.5);
        }

        // Temblor de pantalla háptico visual
        document.body.style.animation = 'screenRumble 0.4s ease-out';
        setTimeout(() => {
            document.body.style.animation = '';
        }, 400);

        // Abrir compuertas mecánicas (blast doors)
        if (doorLeft && doorRight) {
            doorLeft.classList.add('open-sequence');
            doorRight.classList.add('open-sequence');
        }

        // Desvanecer el splash screen con elegancia
        setTimeout(() => {
            if (introScreen) {
                introScreen.classList.add('hide-intro');
            }
            // Notificar al app principal
            window.dispatchEvent(new CustomEvent('rentatools:entered'));
        }, 550);
    }

    // Saltar Intro Directamente
    function skipIntro() {
        if (introScreen) {
            introScreen.classList.add('hide-intro');
        }
        window.dispatchEvent(new CustomEvent('rentatools:entered'));
    }

    // Volver a ver la animación de inicio
    function replayIntro() {
        isLaunching = false;
        if (introScreen) {
            introScreen.classList.remove('hide-intro');
            if (doorLeft && doorRight) {
                doorLeft.classList.remove('open-sequence');
                doorRight.classList.remove('open-sequence');
            }
        }
    }

    // Configuración de Eventos de Click y Teclado
    function setupEventListeners() {
        // Clic en el artefacto mecánico
        if (artifactTarget) {
            artifactTarget.addEventListener('click', triggerEnterSequence);
            artifactTarget.addEventListener('keydown', (e) => {
                if (e.key === 'Enter' || e.key === ' ') {
                    e.preventDefault();
                    triggerEnterSequence();
                }
            });
        }

        // Clic en el botón principal de ignición
        if (btnEnter) {
            btnEnter.addEventListener('click', triggerEnterSequence);
        }

        // Botón saltar intro
        if (btnSkip) {
            btnSkip.addEventListener('click', skipIntro);
        }

        // Repetir intro desde el navbar
        if (btnReplayIntro) {
            btnReplayIntro.addEventListener('click', replayIntro);
        }

        // Alternar sonido sintetizado
        if (btnSoundToggle) {
            btnSoundToggle.addEventListener('click', () => {
                soundEnabled = !soundEnabled;
                if (soundIcon) {
                    soundIcon.textContent = soundEnabled ? '🔊' : '🔇';
                }
                const label = btnSoundToggle.querySelector('.ctrl-label');
                if (label) {
                    label.textContent = soundEnabled ? 'AUDIO FX' : 'MUTED';
                }
            });
        }
    }

    // Inicialización al cargar el DOM
    document.addEventListener('DOMContentLoaded', () => {
        initCanvas();
        setupParallaxInteractions();
        setupEventListeners();
    });

})();
