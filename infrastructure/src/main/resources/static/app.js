/**
 * RENTATOOLS PRO — MAIN APPLICATION ENGINE
 * Gestión de estado, roles (Cliente/Proveedor/Admin), catálogo, reservas, BI y API client con fallback mock.
 */

(function () {
    'use strict';

    // =========================================================================
    // 1. CONFIGURACIÓN Y ESTADO DE LA APLICACIÓN
    // =========================================================================

    const API_BASE_URL = window.location.origin.includes(':8080')
        ? window.location.origin
        : (window.location.port === '' || window.location.port === '80')
            ? ''
            : 'http://localhost:8080';

    // Semillas iniciales ricas y realistas de maquinaria de construcción
    const INITIAL_TOOLS = [
        {
            id: 'tool-001',
            name: 'Retroexcavadora Orugas CAT 420F2',
            brand: 'Caterpillar',
            category: 'EARTHWORK',
            categoryLabel: 'Movimiento de Tierras',
            pricePerDay: 420000,
            depositAmount: 1500000,
            status: 'AVAILABLE',
            image: 'assets/images/tools/cat-420f2-excavator.jpg',
            serialNumber: 'CAT-2024-X812',
            specs: ['Potencia neta: 93 HP (69 kW)', 'Capacidad balde: 1.0 m³', 'Profundidad de excavación: 4.36 m', 'Motor diésel turboalimentado']
        },
        {
            id: 'tool-002',
            name: 'Martillo Demoledor Hexagonal Bosch GSH 27 VC',
            brand: 'Bosch Professional',
            category: 'DEMOLITION',
            categoryLabel: 'Demolición & Perforación',
            pricePerDay: 135000,
            depositAmount: 600000,
            status: 'AVAILABLE',
            image: 'assets/images/tools/bosch-gsh27-jackhammer.jpg',
            serialNumber: 'BSH-DEM-0941',
            specs: ['Energía de impacto: 62 Joules', 'Potencia absorbida: 2000 W', 'Tasa de impacto: 1000 bpm', 'Vibración reducida 8.5 m/s²']
        },
        {
            id: 'tool-003',
            name: 'Generador Diésel Insonorizado 100 kVA Cummins',
            brand: 'Cummins Power',
            category: 'POWER',
            categoryLabel: 'Generación Eléctrica',
            pricePerDay: 380000,
            depositAmount: 1800000,
            status: 'AVAILABLE',
            image: 'assets/images/tools/cummins-generator-100kva.jpg',
            serialNumber: 'CUM-GEN-552',
            specs: ['Salida continua: 80 kW / 100 kVA', 'Voltaje: 220V / 440V Trifásico', 'Tanque de combustible: 240 L', 'Nivel de ruido: 68 dB a 7m']
        },
        {
            id: 'tool-004',
            name: 'Minicargador de Ruedas Bobcat S570',
            brand: 'Bobcat',
            category: 'EARTHWORK',
            categoryLabel: 'Movimiento de Tierras',
            pricePerDay: 310000,
            depositAmount: 1200000,
            status: 'AVAILABLE',
            image: 'assets/images/tools/bobcat-s570-skidsteer.jpg',
            serialNumber: 'BOB-S570-881',
            specs: ['Carga nominal operativa: 885 kg', 'Potencia: 61 HP', 'Altura de volteo: 3.02 m', 'Cabina con aire acondicionado']
        },
        {
            id: 'tool-005',
            name: 'Mezcladora de Concreto 2 Bultos con Motor Diésel',
            brand: 'Imer Group',
            category: 'CONCRETE',
            categoryLabel: 'Concreto & Mezclado',
            pricePerDay: 85000,
            depositAmount: 400000,
            status: 'AVAILABLE',
            image: 'assets/images/tools/imer-concrete-mixer.jpg',
            serialNumber: 'IMR-CON-302',
            specs: ['Capacidad de mezcla: 350 Litros (2 sacos)', 'Motor: Yanmar Diésel 9 HP', 'Chasis de tiro reforzado', 'Tambor de acero de alta resistencia']
        },
        {
            id: 'tool-006',
            name: 'Plataforma Tijera Eléctrica Genie GS-2646',
            brand: 'Genie / Terex',
            category: 'LIFTING',
            categoryLabel: 'Altura & Elevación',
            pricePerDay: 260000,
            depositAmount: 1000000,
            status: 'AVAILABLE',
            image: 'assets/images/tools/genie-scissor-lift.jpg',
            serialNumber: 'GEN-TJR-741',
            specs: ['Altura de trabajo: 9.92 m', 'Capacidad de carga: 454 kg (2 operarios)', 'Neumáticos no marcantes para interior', 'Extensión de plataforma 0.91 m']
        },
        {
            id: 'tool-007',
            name: 'Rodillo Compactador Vibromak 1.5 Toneladas',
            brand: 'Bomag / Wacker',
            category: 'EARTHWORK',
            categoryLabel: 'Movimiento de Tierras',
            pricePerDay: 240000,
            depositAmount: 900000,
            status: 'RENTED',
            image: 'assets/images/tools/vibromak-road-roller.jpg',
            serialNumber: 'BMG-ROD-119',
            specs: ['Peso operativo: 1540 kg', 'Fuerza centrífuga: 25 kN', 'Doble tambor tándem hidrostático', 'Rociador de agua presurizado']
        },
        {
            id: 'tool-008',
            name: 'Torre de Iluminación LED Móvil 4x350W',
            brand: 'Doosan Portable',
            category: 'POWER',
            categoryLabel: 'Generación Eléctrica',
            pricePerDay: 195000,
            depositAmount: 750000,
            status: 'MAINTENANCE',
            image: 'assets/images/tools/doosan-light-tower.jpg',
            serialNumber: 'DOO-LGT-612',
            specs: ['Luminarias: 4 x 350W LED de alto flujo', 'Mástil telescópico: 8.5 metros', 'Autonomía: 60 horas continuas', 'Remolcable en carretera']
        }
    ];

    // Reservas de ejemplo para seguimiento
    const INITIAL_RESERVATIONS = [
        {
            id: 'RES-2026-901',
            toolId: 'tool-007',
            toolName: 'Rodillo Compactador Vibromak 1.5 Ton',
            toolImage: 'assets/images/tools/vibromak-road-roller.jpg',
            startDate: '2026-10-12',
            endDate: '2026-10-18',
            days: 6,
            dailyRate: 240000,
            subtotal: 1440000,
            deposit: 900000,
            total: 2340000,
            siteAddress: 'Calle 140 # 19-30, Proyecto Torre Bambú, Bogotá',
            status: 'DELIVERED', // REQUESTED, CONFIRMED, DELIVERED, RETURNED
            stepIndex: 3,
            contractCode: 'RT-CTR-2026-901'
        }
    ];

    // Estado Reactivo Central
    const state = {
        currentUser: {
            id: 'user-001',
            name: 'Julian Dev',
            email: 'cliente@rentatools.com',
            role: 'CLIENT', // CLIENT, PROVIDER, ADMIN
            token: 'demo-jwt-token-active'
        },
        tools: [...INITIAL_TOOLS],
        reservations: [...INITIAL_RESERVATIONS],
        providerTools: INITIAL_TOOLS.slice(0, 3),
        activeCategory: 'ALL',
        searchQuery: '',
        availabilityFilter: 'ALL',
        priceSort: 'DEFAULT',
        selectedToolForReservation: null,
        metrics: {
            totalRevenue: 48720000,
            activeRentals: 14,
            totalTools: 32,
            occupancyRate: 81.2,
            categoriesRevenue: [
                { category: 'Demolición', amount: 9800000, pct: 60 },
                { category: 'Movimiento Tierras', amount: 18450000, pct: 100 },
                { category: 'Concreto', amount: 6200000, pct: 40 },
                { category: 'Elevación', amount: 8120000, pct: 50 },
                { category: 'Generación', amount: 6150000, pct: 38 }
            ]
        }
    };

    // Formateador de Moneda Colombiana (COP)
    const currencyFormatter = new Intl.NumberFormat('es-CO', {
        style: 'currency',
        currency: 'COP',
        maximumFractionDigits: 0
    });

    // =========================================================================
    // 2. SISTEMA DE TOASTS FLOTANTES
    // =========================================================================

    function showToast(message, type = 'info', duration = 3500) {
        const container = document.getElementById('toast-container');
        if (!container) return;

        const toast = document.createElement('div');
        toast.className = `toast toast-${type}`;
        
        let icon = '<svg width="15" height="15" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"/><line x1="12" y1="16" x2="12" y2="12"/><line x1="12" y1="8" x2="12.01" y2="8"/></svg>';
        if (type === 'success') {
            icon = '<svg width="15" height="15" viewBox="0 0 24 24" fill="none" stroke="#16a34a" stroke-width="2"><path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"/><polyline points="22 4 12 14.01 9 11.01"/></svg>';
        }
        if (type === 'error') {
            icon = '<svg width="15" height="15" viewBox="0 0 24 24" fill="none" stroke="#dc2626" stroke-width="2"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/></svg>';
        }

        toast.innerHTML = `
            <span class="toast-icon">${icon}</span>
            <div class="toast-content">${message}</div>
        `;

        container.appendChild(toast);

        setTimeout(() => {
            toast.style.opacity = '0';
            toast.style.transform = 'translateX(50px)';
            toast.style.transition = 'all 0.3s ease';
            setTimeout(() => toast.remove(), 300);
        }, duration);
    }

    // =========================================================================
    // 3. CAPA DE INTEGRACIÓN API (CON FALLBACK MOCK RESILIENTE)
    // =========================================================================

    async function apiRequest(endpoint, options = {}) {
        const headers = {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...(options.headers || {})
        };

        if (state.currentUser && state.currentUser.token) {
            headers['Authorization'] = `Bearer ${state.currentUser.token}`;
        }

        try {
            const res = await fetch(`${API_BASE_URL}${endpoint}`, {
                ...options,
                headers
            });

            if (!res.ok) {
                const errJson = await res.json().catch(() => ({ message: res.statusText }));
                throw new Error(errJson.message || `Error HTTP ${res.status}`);
            }

            return await res.json().catch(() => ({ success: true }));
        } catch (err) {
            console.warn(`[RentaTools API]: Llamada offline a ${endpoint}. Activando fallback local. Motivo:`, err.message);
            return null; // Fallback to local state
        }
    }

    // Carga de catálogo desde backend si está encendido
    async function syncCatalogWithBackend() {
        const backendTools = await apiRequest('/api/tools');
        if (backendTools && Array.isArray(backendTools) && backendTools.length > 0) {
            state.tools = backendTools.map(bt => ({
                id: bt.id || `bt-${Math.random()}`,
                name: bt.name,
                brand: bt.brand || 'Industrial',
                category: bt.category || 'EARTHWORK',
                categoryLabel: bt.category || 'Maquinaria',
                pricePerDay: bt.pricePerDay || 200000,
                depositAmount: bt.depositAmount || 800000,
                status: bt.status || 'AVAILABLE',
                image: bt.imageUrl || 'assets/images/tools/cat-420f2-excavator.jpg',
                serialNumber: bt.serialNumber || 'SN-2026',
                specs: bt.specs || ['Equipo certificado', 'Inspección técnica al día']
            }));
            renderCatalog();
            showToast('Catálogo sincronizado con el backend Spring Boot', 'success');
        }
    }

    // =========================================================================
    // 4. RENDERIZADO DEL CATÁLOGO & FILTROS
    // =========================================================================

    function filterAndSortTools() {
        return state.tools.filter(tool => {
            // Filtro por categoría
            const matchCat = state.activeCategory === 'ALL' || tool.category === state.activeCategory;
            // Filtro por disponibilidad
            const matchAvail = state.availabilityFilter === 'ALL' || tool.status === state.availabilityFilter;
            // Filtro por texto de búsqueda
            const q = state.searchQuery.toLowerCase().trim();
            const matchSearch = !q ||
                tool.name.toLowerCase().includes(q) ||
                tool.brand.toLowerCase().includes(q) ||
                (tool.serialNumber && tool.serialNumber.toLowerCase().includes(q));

            return matchCat && matchAvail && matchSearch;
        }).sort((a, b) => {
            if (state.priceSort === 'PRICE_ASC') return a.pricePerDay - b.pricePerDay;
            if (state.priceSort === 'PRICE_DESC') return b.pricePerDay - a.pricePerDay;
            return 0;
        });
    }

    function renderCatalog() {
        const container = document.getElementById('tools-grid-container');
        const countNum = document.getElementById('catalog-count-num');
        if (!container) return;

        const filtered = filterAndSortTools();
        if (countNum) countNum.textContent = filtered.length;

        if (filtered.length === 0) {
            container.innerHTML = `
                <div class="empty-state-card" style="grid-column: 1 / -1;">
                    <svg width="36" height="36" viewBox="0 0 24 24" fill="none" stroke="#94a3b8" stroke-width="1.5" style="margin: 0 auto 10px; display: block;"><circle cx="11" cy="11" r="8"/><line x1="21" y1="21" x2="16.65" y2="16.65"/></svg>
                    <h3>No se encontraron maquinarias</h3>
                    <p>Prueba modificando los filtros de categoría o el término de búsqueda.</p>
                </div>
            `;
            return;
        }

        container.innerHTML = filtered.map(tool => {
            const isAvail = tool.status === 'AVAILABLE';
            let statusClass = 'status-available';
            let statusText = 'DISPONIBLE';
            if (tool.status === 'RENTED') {
                statusClass = 'status-rented';
                statusText = 'EN RENTA';
            } else if (tool.status === 'MAINTENANCE') {
                statusClass = 'status-maint';
                statusText = 'EN MANTENIMIENTO';
            }

            const specsHtml = tool.specs.slice(0, 3).map(s => `
                <li><span class="spec-bullet">•</span> ${s}</li>
            `).join('');

            return `
                <article class="tool-card" data-tool-id="${tool.id}">
                    <div class="tool-badge-row">
                        <span class="tool-status-badge ${statusClass}">${statusText}</span>
                        <span class="tool-category-badge">${tool.categoryLabel}</span>
                    </div>

                    <div class="tool-visual-box">
                        <img class="tool-photo" src="${tool.image}" alt="${tool.name}" loading="lazy">
                    </div>

                    <div class="tool-info-body">
                        <span class="tool-brand-tag">${tool.brand.toUpperCase()}</span>
                        <h3 class="tool-title">${tool.name}</h3>

                        <ul class="tool-specs-list">
                            ${specsHtml}
                        </ul>

                        <div class="tool-pricing-footer">
                            <div class="tool-rate-block">
                                <span class="tool-rate-val">${currencyFormatter.format(tool.pricePerDay)}</span>
                                <span class="tool-rate-period">Tarifa por día • Depósito: ${currencyFormatter.format(tool.depositAmount)}</span>
                            </div>
                            <button class="btn-rent-card" data-action="rent" data-tool-id="${tool.id}" ${!isAvail ? 'disabled' : ''}>
                                ${isAvail ? 'Rentar Equipo' : 'No disponible'}
                            </button>
                        </div>
                    </div>
                </article>
            `;
        }).join('');
    }

    // =========================================================================
    // 5. MODAL DE RESERVA & LIQUIDACIÓN DE ALQUILER
    // =========================================================================

    const modalRes = document.getElementById('modal-reservation');
    const formRes = document.getElementById('form-create-reservation');
    const resStartDate = document.getElementById('res-start-date');
    const resEndDate = document.getElementById('res-end-date');
    const calcDays = document.getElementById('calc-days');
    const calcSubtotal = document.getElementById('calc-subtotal');
    const calcDeposit = document.getElementById('calc-deposit');
    const calcTotal = document.getElementById('calc-total');

    function openReservationModal(toolId) {
        const tool = state.tools.find(t => t.id === toolId);
        if (!tool) return;
        state.selectedToolForReservation = tool;

        // Llenar vista previa del equipo
        document.getElementById('res-tool-icon').innerHTML = `<img src="${tool.image}" alt="${tool.name}" class="tool-summary-photo">`;
        document.getElementById('res-tool-name').textContent = tool.name;
        document.getElementById('res-tool-specs').textContent = `${tool.brand} • Serie: ${tool.serialNumber}`;
        document.getElementById('res-tool-rate').textContent = `${currencyFormatter.format(tool.pricePerDay)} / día`;
        document.getElementById('res-tool-deposit').textContent = `Depósito: ${currencyFormatter.format(tool.depositAmount)}`;

        // Fechas por defecto (Mañana por 3 días)
        const tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        const endDay = new Date();
        endDay.setDate(endDay.getDate() + 4);

        resStartDate.value = tomorrow.toISOString().split('T')[0];
        resEndDate.value = endDay.toISOString().split('T')[0];
        resStartDate.min = tomorrow.toISOString().split('T')[0];

        recalculateRentalTotals();

        modalRes.classList.remove('hidden');
    }

    function recalculateRentalTotals() {
        if (!state.selectedToolForReservation) return;
        const tool = state.selectedToolForReservation;

        const start = new Date(resStartDate.value);
        const end = new Date(resEndDate.value);

        let days = 1;
        if (!isNaN(start) && !isNaN(end) && end >= start) {
            const diffTime = Math.abs(end - start);
            days = Math.max(1, Math.ceil(diffTime / (1000 * 60 * 60 * 24)));
        }

        const subtotal = days * tool.pricePerDay;
        const deposit = tool.depositAmount;
        const total = subtotal + deposit;

        calcDays.textContent = `${days} día${days > 1 ? 's' : ''}`;
        calcSubtotal.textContent = `${currencyFormatter.format(subtotal)} COP`;
        calcDeposit.textContent = `${currencyFormatter.format(deposit)} COP`;
        calcTotal.textContent = `${currencyFormatter.format(total)} COP`;
    }

    async function handleReservationSubmit(e) {
        e.preventDefault();
        const tool = state.selectedToolForReservation;
        if (!tool) return;

        const start = resStartDate.value;
        const end = resEndDate.value;
        const address = document.getElementById('res-project-address').value;
        const notes = document.getElementById('res-notes').value;

        const startD = new Date(start);
        const endD = new Date(end);
        const days = Math.max(1, Math.ceil(Math.abs(endD - startD) / (1000 * 60 * 60 * 24)));

        const newRes = {
            id: `RES-${Date.now().toString().slice(-4)}`,
            toolId: tool.id,
            toolName: tool.name,
            toolImage: tool.image,
            startDate: start,
            endDate: end,
            days: days,
            dailyRate: tool.pricePerDay,
            subtotal: days * tool.pricePerDay,
            deposit: tool.depositAmount,
            total: (days * tool.pricePerDay) + tool.depositAmount,
            siteAddress: address,
            notes: notes,
            status: 'REQUESTED',
            stepIndex: 1,
            contractCode: `RT-CTR-${Date.now().toString().slice(-6)}`
        };

        // Enviar a la API Spring Boot si está activo
        const apiPayload = {
            clientId: state.currentUser.id,
            toolId: tool.id,
            startDate: start,
            endDate: end,
            siteAddress: address
        };
        await apiRequest('/api/reservations', {
            method: 'POST',
            body: JSON.stringify(apiPayload)
        });

        // Actualizar estado local
        state.reservations.unshift(newRes);
        tool.status = 'RENTED'; // Marcar como rentada
        modalRes.classList.add('hidden');

        renderCatalog();
        renderClientRentals();
        updateRentalsBadge();

        showToast(`¡Solicitud generada con éxito! Ref: ${newRes.id}`, 'success');

        // Cambiar automáticamente a la pestaña "Mis Alquileres"
        switchTab('section-client-rentals');
    }

    // =========================================================================
    // 6. RENDERIZADO DE ALQUILERES & SEGUIMIENTO (CLIENTE)
    // =========================================================================

    function updateRentalsBadge() {
        const badge = document.getElementById('active-rentals-count');
        if (badge) {
            badge.textContent = state.reservations.length;
        }
    }

    function renderClientRentals() {
        const container = document.getElementById('client-rentals-container');
        if (!container) return;

        if (state.reservations.length === 0) {
            container.innerHTML = `
                <div class="empty-state-card">
                    <svg width="36" height="36" viewBox="0 0 24 24" fill="none" stroke="#94a3b8" stroke-width="1.5" style="margin: 0 auto 10px; display: block;"><path d="M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z"/><polyline points="3.27 6.96 12 12.01 20.73 6.96"/><line x1="12" y1="22.08" x2="12" y2="12"/></svg>
                    <h3>No tienes alquileres en curso</h3>
                    <p>Explora el catálogo y solicita tu primera maquinaria pesada hoy mismo.</p>
                </div>
            `;
            return;
        }

        container.innerHTML = state.reservations.map(res => {
            const steps = [
                { label: '1. Solicitada', completed: res.stepIndex >= 1, current: res.stepIndex === 1 },
                { label: '2. Confirmada & Paga', completed: res.stepIndex >= 2, current: res.stepIndex === 2 },
                { label: '3. En Obra', completed: res.stepIndex >= 3, current: res.stepIndex === 3 },
                { label: '4. Finalizada', completed: res.stepIndex >= 4, current: res.stepIndex === 4 }
            ];

            const stepsHtml = steps.map(s => `
                <div class="step-node ${s.completed ? 'completed' : ''} ${s.current ? 'current' : ''}">
                    <div class="step-circle">${s.completed ? '✓' : ''}</div>
                    <span class="step-label">${s.label}</span>
                </div>
            `).join('');

            const progressPct = ((res.stepIndex - 1) / 3) * 100;

            let actionBtnHtml = '';
            if (res.status === 'REQUESTED') {
                actionBtnHtml = `
                    <button class="btn-primary" data-action="pay-confirm" data-res-id="${res.id}">
                        Pagar Depósito &amp; Confirmar
                    </button>
                `;
            } else if (res.status === 'CONFIRMED') {
                actionBtnHtml = `
                    <button class="btn-secondary" data-action="dispatch-site" data-res-id="${res.id}">
                        Registrar Llegada a Obra
                    </button>
                `;
            } else if (res.status === 'DELIVERED') {
                actionBtnHtml = `
                    <button class="btn-secondary" data-action="return-tool" data-res-id="${res.id}">
                        Notificar Devolución
                    </button>
                `;
            }

            return `
                <div class="rental-order-card" data-res-id="${res.id}">
                    <div class="rental-order-header">
                        <div class="rental-code-block">
                            <span class="pulse-indicator"></span>
                            <span class="rental-code">RESERVA #${res.id}</span>
                            <span class="status-available" style="padding: 2px 8px; border-radius: 999px; font-size: 0.72rem;">${res.status}</span>
                        </div>
                        <div class="rental-dates-tag">
                            <span>Periodo: <strong>${res.startDate}</strong> al <strong>${res.endDate}</strong> (${res.days} días)</span>
                        </div>
                    </div>

                    <div class="rental-item-info">
                        <div class="rental-item-icon"><img src="${res.toolImage}" alt="${res.toolName}" class="rental-item-photo"></div>
                        <div>
                            <h3 class="rental-item-name">${res.toolName}</h3>
                            <div class="rental-item-dates">Entrega en obra: ${res.siteAddress}</div>
                        </div>
                    </div>

                    <!-- Stepper de 4 Fases -->
                    <div class="stepper-container">
                        <div class="stepper-line">
                            <div class="stepper-line-progress" style="width: ${progressPct}%;"></div>
                        </div>
                        ${stepsHtml}
                    </div>

                    <div class="rental-order-actions">
                        <div class="rental-total-amount">
                            Total Liquidado: ${currencyFormatter.format(res.total)} COP
                        </div>
                        <div class="rental-buttons-group">
                            <button class="btn-secondary" data-action="view-contract" data-res-id="${res.id}">
                                Ver Contrato &amp; Acta
                            </button>
                            ${actionBtnHtml}
                        </div>
                    </div>
                </div>
            `;
        }).join('');
    }

    // Acciones sobre las reservas
    async function handleRentalAction(action, resId) {
        const res = state.reservations.find(r => r.id === resId);
        if (!res) return;

        if (action === 'pay-confirm') {
            // Llamar API de pagos y confirmación
            await apiRequest('/api/payments', {
                method: 'POST',
                body: JSON.stringify({
                    reservationId: res.id,
                    amount: res.total,
                    currency: 'COP',
                    paymentMethod: 'CREDIT_CARD'
                })
            });
            await apiRequest(`/api/reservations/${res.id}/confirm`, { method: 'PUT' });

            res.status = 'CONFIRMED';
            res.stepIndex = 2;
            renderClientRentals();
            showToast(`Pago procesado. ¡Reserva #${res.id} confirmada!`, 'success');
        } else if (action === 'dispatch-site') {
            res.status = 'DELIVERED';
            res.stepIndex = 3;
            renderClientRentals();
            showToast(`Equipo en obra. Acta de entrega firmada electrónicamente.`, 'success');
        } else if (action === 'return-tool') {
            res.status = 'RETURNED';
            res.stepIndex = 4;
            const tool = state.tools.find(t => t.id === res.toolId);
            if (tool) tool.status = 'AVAILABLE';
            renderCatalog();
            renderClientRentals();
            showToast(`Devolución completada. Depósito en garantía liberado.`, 'success');
        } else if (action === 'view-contract') {
            openContractModal(res);
        }
    }

    // Modal de Contrato y Acta PDF
    function openContractModal(res) {
        const modal = document.getElementById('modal-contract-view');
        const code = document.getElementById('contract-code');
        const content = document.getElementById('contract-body-content');
        if (!modal) return;

        code.textContent = `REF: ${res.contractCode || 'RT-CTR-2026'}`;
        content.innerHTML = `
            <p><strong>ARRENDADOR:</strong> RentaTools PRO Colombia S.A.S. (NIT 901.849.201-4)</p>
            <p><strong>ARRENDATARIO:</strong> ${state.currentUser.name} (${state.currentUser.email})</p>
            <p><strong>EQUIPO IDENTIFICADO:</strong> ${res.toolName}</p>
            <p><strong>DIRECCIÓN DE OBRA:</strong> ${res.siteAddress}</p>
            <p><strong>VIGENCIA:</strong> Del ${res.startDate} al ${res.endDate} (${res.days} días calendario).</p>
            <hr style="margin: 12px 0; border: 0; border-top: 1px solid #cbd5e1;">
            <p><strong>LIQUIDACIÓN COMERCIAL:</strong></p>
            <ul>
                <li>Tarifa Diaria: ${currencyFormatter.format(res.dailyRate)} COP</li>
                <li>Subtotal Renta: ${currencyFormatter.format(res.subtotal)} COP</li>
                <li>Depósito de Seguridad: ${currencyFormatter.format(res.deposit)} COP</li>
                <li><strong>TOTAL COMPROMETIDO: ${currencyFormatter.format(res.total)} COP</strong></li>
            </ul>
            <hr style="margin: 12px 0; border: 0; border-top: 1px solid #cbd5e1;">
            <p><strong>CLÁUSULAS ESPECIALES:</strong> El arrendatario asume la custodia del equipo y se compromete a operarlo conforme al manual de fabricante. Póliza Todo Riesgo activa por valor asegurado de $150.000.000 COP.</p>
            <div style="margin-top: 16px; display: flex; justify-content: space-between; font-size: 0.75rem; color: #64748b;">
                <div>Firma Digital Arrendador: <strong>HASH_VERIFIED_7a89e</strong></div>
                <div>Firma Digital Cliente: <strong>HASH_VERIFIED_4fe35</strong></div>
            </div>
        `;

        modal.classList.remove('hidden');
    }

    // =========================================================================
    // 7. PANEL DE PROVEEDOR
    // =========================================================================

    function renderProviderPanel() {
        const list = document.getElementById('provider-tools-list');
        const count = document.getElementById('provider-tools-count');
        if (!list) return;

        if (count) count.textContent = state.providerTools.length;

        list.innerHTML = state.providerTools.map(tool => `
            <div class="provider-tool-item">
                <div class="provider-tool-thumb">
                    <img src="${tool.image}" alt="${tool.name}" class="provider-tool-photo">
                </div>
                <div class="provider-tool-meta">
                    <h4>${tool.name}</h4>
                    <span>${tool.brand} • ${currencyFormatter.format(tool.pricePerDay)}/día • Serie: ${tool.serialNumber}</span>
                </div>
                <span class="status-available" style="padding: 3px 10px; border-radius: 999px; font-size: 0.72rem;">
                    ${tool.status}
                </span>
            </div>
        `).join('');
    }

    async function handleCreateToolSubmit(e) {
        e.preventDefault();
        const name = document.getElementById('new-tool-name').value;
        const category = document.getElementById('new-tool-category').value;
        const price = parseInt(document.getElementById('new-tool-price').value, 10);
        const deposit = parseInt(document.getElementById('new-tool-deposit').value, 10);
        const serial = document.getElementById('new-tool-serial').value;
        const brand = document.getElementById('new-tool-brand').value;
        const specsText = document.getElementById('new-tool-specs').value;

        const newTool = {
            id: `tool-${Date.now().toString().slice(-4)}`,
            name,
            brand,
            category,
            categoryLabel: category === 'DEMOLITION' ? 'Demolición' : 'Maquinaria',
            pricePerDay: price,
            depositAmount: deposit,
            status: 'AVAILABLE',
            image: category === 'DEMOLITION' ? 'assets/images/tools/bosch-gsh27-jackhammer.jpg' : category === 'LIFTING' ? 'assets/images/tools/genie-scissor-lift.jpg' : 'assets/images/tools/cat-420f2-excavator.jpg',
            serialNumber: serial,
            specs: specsText ? specsText.split(',').map(s => s.trim()) : ['Equipo verificado', 'Mantenimiento al día']
        };

        // Enviar a la API Spring Boot si está activo
        await apiRequest('/api/tools', {
            method: 'POST',
            body: JSON.stringify(newTool)
        });

        state.tools.unshift(newTool);
        state.providerTools.unshift(newTool);

        e.target.reset();
        renderCatalog();
        renderProviderPanel();
        showToast(`¡Equipo "${name}" publicado con éxito en el catálogo!`, 'success');
    }

    // =========================================================================
    // 8. PANEL DE ANALÍTICAS & BI (ADMIN)
    // =========================================================================

    async function syncMetricsFromBackend() {
        const backendMetrics = await apiRequest('/api/reports/metrics');
        if (backendMetrics) {
            state.metrics.totalRevenue = backendMetrics.totalRevenue || state.metrics.totalRevenue;
            state.metrics.activeRentals = backendMetrics.activeRentals || state.metrics.activeRentals;
            state.metrics.totalTools = backendMetrics.totalTools || state.metrics.totalTools;
            state.metrics.occupancyRate = backendMetrics.occupancyRate || state.metrics.occupancyRate;
        }
        renderAnalytics();
    }

    function renderAnalytics() {
        const kpiRev = document.getElementById('kpi-revenue');
        const kpiRentals = document.getElementById('kpi-active-rentals');
        const kpiTools = document.getElementById('kpi-total-tools');
        const kpiOcc = document.getElementById('kpi-occupancy');

        if (kpiRev) kpiRev.textContent = `${currencyFormatter.format(state.metrics.totalRevenue)} COP`;
        if (kpiRentals) kpiRentals.textContent = state.metrics.activeRentals;
        if (kpiTools) kpiTools.textContent = `${state.metrics.totalTools} Equipos`;
        if (kpiOcc) kpiOcc.textContent = `${state.metrics.occupancyRate}%`;

        // Gráfico de Barras por Categoría
        const barContainer = document.getElementById('bar-chart-container');
        if (barContainer) {
            barContainer.innerHTML = state.metrics.categoriesRevenue.map(c => `
                <div class="chart-bar-col">
                    <span class="chart-bar-val">${(c.amount / 1000000).toFixed(1)}M</span>
                    <div class="chart-bar-fill" style="height: ${c.pct}%;"></div>
                    <span class="chart-bar-label">${c.category}</span>
                </div>
            `).join('');
        }
    }

    // =========================================================================
    // 9. GESTIÓN DE ROLES & AUTENTICACIÓN
    // =========================================================================

    function switchRole(newRole) {
        state.currentUser.role = newRole;
        if (newRole === 'CLIENT') {
            state.currentUser.name = 'Julian Contratista';
            state.currentUser.email = 'cliente@rentatools.com';
        } else if (newRole === 'PROVIDER') {
            state.currentUser.name = 'Maquinaria Pesada del Norte S.A.S.';
            state.currentUser.email = 'proveedor@rentatools.com';
        } else if (newRole === 'ADMIN') {
            state.currentUser.name = 'Ing. Administrador de Operaciones';
            state.currentUser.email = 'admin@rentatools.com';
        }

        updateUserUI();

        // Actualizar chips de rol
        document.querySelectorAll('.role-chip').forEach(c => {
            c.classList.toggle('active', c.getAttribute('data-role') === newRole);
        });

        showToast(`Rol cambiado a: ${newRole}`, 'info');
    }

    function updateUserUI() {
        const guestBox = document.getElementById('auth-guest-box');
        const userBox = document.getElementById('auth-user-box');
        const avatar = document.getElementById('user-avatar');
        const displayName = document.getElementById('user-display-name');
        const roleBadge = document.getElementById('user-role-badge');

        if (state.currentUser && state.currentUser.token) {
            if (guestBox) guestBox.classList.add('hidden');
            if (userBox) userBox.classList.remove('hidden');

            const initials = state.currentUser.name
                .split(' ')
                .map(n => n[0])
                .slice(0, 2)
                .join('');
            if (avatar) avatar.textContent = initials.toUpperCase() || 'RT';
            if (displayName) displayName.textContent = state.currentUser.name;
            if (roleBadge) roleBadge.textContent = state.currentUser.role;
        } else {
            if (guestBox) guestBox.classList.remove('hidden');
            if (userBox) userBox.classList.add('hidden');
        }
    }

    // Cambio de Pestañas de Navegación
    function switchTab(targetSectionId) {
        document.querySelectorAll('.nav-tab').forEach(tab => {
            tab.classList.toggle('active', tab.getAttribute('data-target') === targetSectionId);
        });
        document.querySelectorAll('.content-section').forEach(sec => {
            sec.classList.toggle('active', sec.id === targetSectionId);
        });

        // Trigger updates depending on section
        if (targetSectionId === 'section-analytics') {
            renderAnalytics();
        } else if (targetSectionId === 'section-provider') {
            renderProviderPanel();
        } else if (targetSectionId === 'section-client-rentals') {
            renderClientRentals();
        }
    }

    // =========================================================================
    // 10. SETUP DE EVENT LISTENERS
    // =========================================================================

    function initEventListeners() {
        // Navegación Tabs
        document.querySelectorAll('.nav-tab').forEach(tab => {
            tab.addEventListener('click', () => {
                const target = tab.getAttribute('data-target');
                if (target) switchTab(target);
            });
        });

        // Logo volver a catálogo
        const logo = document.getElementById('logo-refresh');
        if (logo) {
            logo.addEventListener('click', () => switchTab('section-catalog'));
        }

        // Roles Rápidos (Demo)
        document.querySelectorAll('.role-chip').forEach(chip => {
            chip.addEventListener('click', () => {
                const role = chip.getAttribute('data-role');
                if (role) {
                    switchRole(role);
                    if (role === 'CLIENT') switchTab('section-catalog');
                    if (role === 'PROVIDER') switchTab('section-provider');
                    if (role === 'ADMIN') switchTab('section-analytics');
                }
            });
        });

        // Buscador y Filtros del Catálogo
        const searchInput = document.getElementById('catalog-search');
        if (searchInput) {
            searchInput.addEventListener('input', (e) => {
                state.searchQuery = e.target.value;
                renderCatalog();
            });
        }

        const availFilter = document.getElementById('filter-availability');
        if (availFilter) {
            availFilter.addEventListener('change', (e) => {
                state.availabilityFilter = e.target.value;
                renderCatalog();
            });
        }

        const priceSort = document.getElementById('sort-price');
        if (priceSort) {
            priceSort.addEventListener('change', (e) => {
                state.priceSort = e.target.value;
                renderCatalog();
            });
        }

        // Píldoras de Categorías
        document.querySelectorAll('.cat-pill').forEach(pill => {
            pill.addEventListener('click', () => {
                document.querySelectorAll('.cat-pill').forEach(p => p.classList.remove('active'));
                pill.classList.add('active');
                state.activeCategory = pill.getAttribute('data-category');
                renderCatalog();
            });
        });

        // Delegación de eventos en el Grid del Catálogo
        const toolsGrid = document.getElementById('tools-grid-container');
        if (toolsGrid) {
            toolsGrid.addEventListener('click', (e) => {
                const btn = e.target.closest('[data-action="rent"]');
                if (btn) {
                    const toolId = btn.getAttribute('data-tool-id');
                    openReservationModal(toolId);
                }
            });
        }

        // Modal de Reserva
        if (resStartDate && resEndDate) {
            resStartDate.addEventListener('change', recalculateRentalTotals);
            resEndDate.addEventListener('change', recalculateRentalTotals);
        }

        if (formRes) {
            formRes.addEventListener('submit', handleReservationSubmit);
        }

        const modalResClose = document.getElementById('modal-res-close');
        const btnCancelRes = document.getElementById('btn-cancel-reservation');
        if (modalResClose) modalResClose.addEventListener('click', () => modalRes.classList.add('hidden'));
        if (btnCancelRes) btnCancelRes.addEventListener('click', () => modalRes.classList.add('hidden'));

        // Delegación de eventos en Mis Alquileres
        const rentalsContainer = document.getElementById('client-rentals-container');
        if (rentalsContainer) {
            rentalsContainer.addEventListener('click', (e) => {
                const btn = e.target.closest('button[data-action]');
                if (btn) {
                    const action = btn.getAttribute('data-action');
                    const resId = btn.getAttribute('data-res-id');
                    handleRentalAction(action, resId);
                }
            });
        }

        const btnNewRentalNav = document.getElementById('btn-new-rental-nav');
        if (btnNewRentalNav) {
            btnNewRentalNav.addEventListener('click', () => switchTab('section-catalog'));
        }

        // Panel Proveedor: Formulario de Creación
        const formCreateTool = document.getElementById('form-create-tool');
        if (formCreateTool) {
            formCreateTool.addEventListener('submit', handleCreateToolSubmit);
        }

        // Modal de Contrato
        const modalContract = document.getElementById('modal-contract-view');
        const modalContractClose = document.getElementById('modal-contract-close');
        const btnCloseContractView = document.getElementById('btn-close-contract-view');
        const btnPrintContract = document.getElementById('btn-print-contract');

        if (modalContractClose) modalContractClose.addEventListener('click', () => modalContract.classList.add('hidden'));
        if (btnCloseContractView) btnCloseContractView.addEventListener('click', () => modalContract.classList.add('hidden'));
        if (btnPrintContract) {
            btnPrintContract.addEventListener('click', () => {
                window.print();
            });
        }

        // Botón Descargar Informe PDF (Admin)
        const btnDownloadPdf = document.getElementById('btn-download-pdf-report');
        if (btnDownloadPdf) {
            btnDownloadPdf.addEventListener('click', () => {
                showToast('Generando informe ejecutivo PDF...', 'info');
                setTimeout(() => {
                    showToast('Informe Gerencial descargado exitosamente: Reporte_Ejecutivo_2026.pdf', 'success');
                }, 1200);
            });
        }

        // Auth Modal & Fast Fills
        const modalAuth = document.getElementById('modal-auth');
        const btnOpenLogin = document.getElementById('btn-open-login');
        const btnOpenRegister = document.getElementById('btn-open-register');
        const modalAuthClose = document.getElementById('modal-auth-close');
        const tabLoginBtn = document.getElementById('tab-login-btn');
        const tabRegisterBtn = document.getElementById('tab-register-btn');
        const formLogin = document.getElementById('form-login');
        const formRegister = document.getElementById('form-register');
        const btnLogout = document.getElementById('btn-logout');

        if (btnOpenLogin) btnOpenLogin.addEventListener('click', () => {
            modalAuth.classList.remove('hidden');
            tabLoginBtn.click();
        });
        if (btnOpenRegister) btnOpenRegister.addEventListener('click', () => {
            modalAuth.classList.remove('hidden');
            tabRegisterBtn.click();
        });
        if (modalAuthClose) modalAuthClose.addEventListener('click', () => modalAuth.classList.add('hidden'));

        if (tabLoginBtn) tabLoginBtn.addEventListener('click', () => {
            tabLoginBtn.classList.add('active');
            tabRegisterBtn.classList.remove('active');
            formLogin.classList.remove('hidden');
            formRegister.classList.add('hidden');
        });

        if (tabRegisterBtn) tabRegisterBtn.addEventListener('click', () => {
            tabRegisterBtn.classList.add('active');
            tabLoginBtn.classList.remove('active');
            formRegister.classList.remove('hidden');
            formLogin.classList.add('hidden');
        });

        // Fast Fill Demo Buttons
        document.querySelectorAll('.btn-quick-fill').forEach(btn => {
            btn.addEventListener('click', () => {
                const email = btn.getAttribute('data-email');
                const role = btn.getAttribute('data-role');
                const emailInput = document.getElementById('login-email');
                if (emailInput) emailInput.value = email;
                switchRole(role);
                showToast(`Credenciales cargadas para: ${role}`, 'info');
            });
        });

        if (formLogin) {
            formLogin.addEventListener('submit', async (e) => {
                e.preventDefault();
                const email = document.getElementById('login-email').value;
                const password = document.getElementById('login-password').value;

                // Intentar auth real en backend
                const authRes = await apiRequest('/api/auth/login', {
                    method: 'POST',
                    body: JSON.stringify({ email, password })
                });

                if (authRes && authRes.token) {
                    state.currentUser.token = authRes.token;
                    state.currentUser.email = email;
                } else {
                    // Fallback demo mode
                    state.currentUser.token = `jwt-token-${Date.now()}`;
                    state.currentUser.email = email;
                }

                updateUserUI();
                modalAuth.classList.add('hidden');
                showToast(`Sesión iniciada correctamente como ${state.currentUser.email}`, 'success');
            });
        }

        if (formRegister) {
            formRegister.addEventListener('submit', async (e) => {
                e.preventDefault();
                const name = document.getElementById('reg-name').value;
                const email = document.getElementById('reg-email').value;
                const role = document.getElementById('reg-role').value;

                await apiRequest('/api/users', {
                    method: 'POST',
                    body: JSON.stringify({ name, email, role, password: 'Password123*' })
                });

                state.currentUser.name = name;
                state.currentUser.email = email;
                state.currentUser.role = role;
                state.currentUser.token = `jwt-token-${Date.now()}`;

                updateUserUI();
                modalAuth.classList.add('hidden');
                showToast(`¡Cuenta registrada exitosamente! Bienvenido ${name}`, 'success');
            });
        }

        if (btnLogout) {
            btnLogout.addEventListener('click', () => {
                state.currentUser.token = '';
                updateUserUI();
                showToast('Has cerrado sesión.', 'info');
            });
        }

        // Listener cuando el usuario entra desde el intro
        window.addEventListener('rentatools:entered', () => {
            showToast('¡Bienvenido a RentaTools PRO! Sistemas hidráulicos listos.', 'success', 4000);
        });
    }

    // Inicialización Principal de la Aplicación
    document.addEventListener('DOMContentLoaded', () => {
        updateUserUI();
        renderCatalog();
        renderClientRentals();
        renderProviderPanel();
        renderAnalytics();
        updateRentalsBadge();
        initEventListeners();

        // Sincronización asíncrona con el backend si está corriendo
        syncCatalogWithBackend();
        syncMetricsFromBackend();
    });

})();
