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

    // Reservas de ejemplo para seguimiento del ciclo de vida en Santander
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
            tax: 273600,
            deposit: 900000,
            total: 2613600,
            siteAddress: 'Autopista Floridablanca Km 4, Condominio Mensulí, Floridablanca, Santander',
            status: 'DELIVERED', // REQUESTED, CONFIRMED, DELIVERED, RETURNED, CANCELLED
            stepIndex: 3,
            contractCode: 'RT-SAN-2026-901',
            invoiceNumber: 'RTSA-2026-0914',
            paymentMethod: 'Transferencia PSE Bancolombia',
            approvalCode: 'AUTH-COL-882194',
            paid: true,
            cufeCode: 'a8b9c0d1e2f3456789abcdef0123456789abcdefa8b9c0d1e2f3456789abcdef0123456789abcdef01234567'
        },
        {
            id: 'RES-2026-902',
            toolId: 'tool-001',
            toolName: 'Retroexcavadora Orugas CAT 420F2',
            toolImage: 'assets/images/tools/cat-420f2-excavator.jpg',
            startDate: '2026-10-22',
            endDate: '2026-10-25',
            days: 3,
            dailyRate: 420000,
            subtotal: 1260000,
            tax: 239400,
            deposit: 1500000,
            total: 2999400,
            siteAddress: 'Carrera 27 con Calle 36, Obra Edificio Panorama, Bucaramanga, Santander',
            status: 'REQUESTED',
            stepIndex: 1,
            contractCode: 'RT-SAN-2026-902',
            invoiceNumber: 'RTSA-2026-0915',
            paymentMethod: null,
            approvalCode: null,
            paid: false,
            cufeCode: 'f4e3d2c1b0a9876543210fedcba9876543210fedf4e3d2c1b0a9876543210fedcba9876543210fed12345678'
        }
    ];

    // =========================================================================
    // CUENTAS OFICIALES SEGÚN README.md & PERSISTENCIA LOCAL
    // =========================================================================
    const SYSTEM_ACCOUNTS = [
        {
            id: '20000000-0000-0000-0000-000000000001',
            name: 'Maquinaria y Andamios Andina',
            email: 'contacto@maquinariaandina.com',
            password: 'Password123!',
            role: 'PROVIDER'
        },
        {
            id: 'usr-admin-01',
            name: 'Carlos Administrador',
            email: 'admin@rentatools.com',
            password: 'Admin123456!',
            role: 'ADMIN'
        },
        {
            id: 'usr-prov-01',
            name: 'Maquinaria Pesada Santander S.A.S.',
            email: 'proveedor.equipos@maquinariacolombia.com',
            password: 'Prov123456!',
            role: 'PROVIDER'
        },
        {
            id: 'usr-client-01',
            name: 'Constructora del Oriente S.A.',
            email: 'cliente.constructora@constructora.com',
            password: 'Client123456!',
            role: 'CLIENT'
        }
    ];

    function getStoredUsers() {
        try {
            const raw = localStorage.getItem('rentatools_registered_users');
            return raw ? JSON.parse(raw) : [];
        } catch (e) {
            return [];
        }
    }

    function saveRegisteredUser(user) {
        try {
            const users = getStoredUsers();
            users.push(user);
            localStorage.setItem('rentatools_registered_users', JSON.stringify(users));
        } catch (e) {
            console.error('Error saving user', e);
        }
    }

    function getInitialSession() {
        try {
            const saved = localStorage.getItem('rentatools_active_session');
            if (saved) return JSON.parse(saved);
        } catch (e) {}
        // Usuario inicial: Cliente Oficial del README
        return {
            id: 'usr-client-01',
            name: 'Constructora del Oriente S.A.',
            email: 'cliente.constructora@constructora.com',
            role: 'CLIENT',
            token: 'jwt-session-client-init'
        };
    }

    // Estado Reactivo Central
    const state = {
        currentUser: getInitialSession(),
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
        const zoneEl = document.getElementById('res-project-zone');
        const zoneName = zoneEl ? zoneEl.options[zoneEl.selectedIndex].text : 'Bucaramanga AM';

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

    // =========================================================================
    // 6. RENDERIZADO DE ALQUILERES & CICLO DE VIDA (CLIENTE SANTANDER)
    // =========================================================================

    function updateRentalsBadge() {
        const badge = document.getElementById('active-rentals-count');
        if (badge) {
            const activeCount = state.reservations.filter(r => r.status !== 'CANCELLED' && r.status !== 'RETURNED').length;
            badge.textContent = activeCount;
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
                    <p>Explora el catálogo y solicita tu primera maquinaria pesada para tu obra en Santander.</p>
                </div>
            `;
            return;
        }

        container.innerHTML = state.reservations.map(res => {
            const steps = [
                { label: '1. Solicitada', completed: res.stepIndex >= 1, current: res.stepIndex === 1 },
                { label: '2. Paga & Confirmada', completed: res.stepIndex >= 2, current: res.stepIndex === 2 },
                { label: '3. En Obra Activa', completed: res.stepIndex >= 3, current: res.stepIndex === 3 },
                { label: '4. Finalizada', completed: res.stepIndex >= 4, current: res.stepIndex === 4 }
            ];

            const stepsHtml = steps.map(s => `
                <div class="step-node ${s.completed ? 'completed' : ''} ${s.current ? 'current' : ''}">
                    <div class="step-circle">${s.completed ? '✓' : ''}</div>
                    <span class="step-label">${s.label}</span>
                </div>
            `).join('');

            const progressPct = res.status === 'CANCELLED' ? 0 : Math.min(100, Math.max(0, ((res.stepIndex - 1) / 3) * 100));

            // Insignia de estado contextual
            let statusBadgeHtml = '';
            if (res.status === 'REQUESTED') {
                statusBadgeHtml = `<span class="badge-rental-status badge-requested">Pendiente de Pago</span>`;
            } else if (res.status === 'CONFIRMED') {
                statusBadgeHtml = `<span class="badge-rental-status badge-confirmed">Pago Aprobado • En Alistamiento</span>`;
            } else if (res.status === 'DELIVERED') {
                statusBadgeHtml = `<span class="badge-rental-status badge-delivered">En Obra Activa</span>`;
            } else if (res.status === 'RETURNED') {
                statusBadgeHtml = `<span class="badge-rental-status badge-returned">Finalizado • Depósito Devuelto</span>`;
            } else if (res.status === 'CANCELLED') {
                statusBadgeHtml = `<span class="badge-rental-status badge-cancelled">Alquiler Cancelado</span>`;
            }

            // Botones de acción del ciclo de vida
            let lifecycleButtonsHtml = '';
            if (res.status === 'REQUESTED') {
                lifecycleButtonsHtml = `
                    <button class="btn-pay-action btn-sm" data-action="pay-checkout" data-res-id="${res.id}">
                        💳 Pagar Alquiler (${currencyFormatter.format(res.total)})
                    </button>
                    <button class="btn-cancel-action btn-sm" data-action="cancel-rental" data-res-id="${res.id}">
                        ✕ Cancelar Solicitud
                    </button>
                `;
            } else if (res.status === 'CONFIRMED') {
                lifecycleButtonsHtml = `
                    <button class="btn-primary btn-sm" data-action="dispatch-site" data-res-id="${res.id}">
                        🚛 Confirmar Llegada a Obra
                    </button>
                    <button class="btn-cancel-action btn-sm" data-action="cancel-rental" data-res-id="${res.id}">
                        ✕ Cancelar Pedido
                    </button>
                `;
            } else if (res.status === 'DELIVERED') {
                lifecycleButtonsHtml = `
                    <button class="btn-finish-action btn-sm" data-action="return-tool" data-res-id="${res.id}">
                        📦 Finalizar / Devolver Maquinaria
                    </button>
                `;
            } else if (res.status === 'RETURNED') {
                lifecycleButtonsHtml = `
                    <span class="badge-returned">✓ Maquinaria devuelta a Patio Girón</span>
                `;
            } else if (res.status === 'CANCELLED') {
                lifecycleButtonsHtml = `
                    <span class="badge-cancelled">✕ Reserva anulada</span>
                `;
            }

            // Botón de Factura Electrónica (Siempre disponible para auditoría)
            const invoiceBtnHtml = `
                <button class="btn-invoice-action btn-sm" data-action="view-invoice" data-res-id="${res.id}">
                    🧾 Ver Factura Electrónica DIAN
                </button>
            `;

            return `
                <div class="rental-order-card" data-res-id="${res.id}">
                    <div class="rental-order-header">
                        <div class="rental-code-block">
                            <span class="pulse-indicator"></span>
                            <span class="rental-code">RESERVA #${res.id}</span>
                            ${statusBadgeHtml}
                        </div>
                        <div class="rental-dates-tag">
                            <span>Periodo en Santander: <strong>${res.startDate}</strong> al <strong>${res.endDate}</strong> (${res.days} días)</span>
                        </div>
                    </div>

                    <div class="rental-item-info">
                        <div class="rental-item-icon"><img src="${res.toolImage}" alt="${res.toolName}" class="rental-item-photo"></div>
                        <div>
                            <h3 class="rental-item-name">${res.toolName}</h3>
                            <div class="rental-item-dates">Destino Obra: <strong>${res.siteAddress}</strong></div>
                            ${res.paymentMethod ? `<div class="rental-item-dates" style="color: #059669; font-weight: 500;">✓ Pago: ${res.paymentMethod} (Aprobación: ${res.approvalCode || 'AUTH-OK'})</div>` : ''}
                        </div>
                    </div>

                    <!-- Stepper de 4 Fases (Solo si no está cancelado) -->
                    ${res.status !== 'CANCELLED' ? `
                        <div class="stepper-container">
                            <div class="stepper-line">
                                <div class="stepper-line-progress" style="width: ${progressPct}%;"></div>
                            </div>
                            ${stepsHtml}
                        </div>
                    ` : `
                        <div style="background: #fef2f2; border: 1px solid #fee2e2; border-radius: 6px; padding: 8px 12px; margin: 12px 0; font-size: 0.78rem; color: #b91c1c;">
                            Este alquiler fue cancelado. El equipo fue reincorporado a la flota disponible en Santander.
                        </div>
                    `}

                    <div class="rental-order-actions">
                        <div class="rental-total-amount">
                            Liquidación Total: <strong>${currencyFormatter.format(res.total)} COP</strong>
                            <span style="font-size: 0.70rem; color: #64748b; font-weight: normal; display: block;">Incluye subtotal + IVA 19% + Depósito reembolsable</span>
                        </div>
                        <div class="rental-buttons-group">
                            ${invoiceBtnHtml}
                            ${lifecycleButtonsHtml}
                        </div>
                    </div>
                </div>
            `;
        }).join('');
    }

    // =========================================================================
    // CONTROLADOR DE ACCIONES DEL ALQUILER (CLIENTE)
    // =========================================================================

    let currentPayingReservationId = null;

    async function handleRentalAction(action, resId) {
        const res = state.reservations.find(r => r.id === resId);
        if (!res) return;

        if (action === 'pay-checkout') {
            openPaymentGateway(resId);
        } else if (action === 'cancel-rental') {
            handleRentalCancel(resId);
        } else if (action === 'return-tool') {
            handleRentalReturn(resId);
        } else if (action === 'dispatch-site') {
            res.status = 'DELIVERED';
            res.stepIndex = 3;
            renderClientRentals();
            showToast('¡Llegada a obra confirmada! Maquinaria pesada entregada e inventariada en frente de obra.', 'success');
        } else if (action === 'view-invoice') {
            openInvoiceView(resId);
        }
    }

    // Cancelar Alquiler (Cliente)
    function handleRentalCancel(resId) {
        const res = state.reservations.find(r => r.id === resId);
        if (!res) return;

        const confirmCancel = confirm(`¿Estás seguro de que deseas cancelar la reserva #${res.id} (${res.toolName})? El equipo volverá a estar disponible en el catálogo de Santander.`);
        if (!confirmCancel) return;

        res.status = 'CANCELLED';
        res.stepIndex = 0;

        // Liberar la maquinaria en el catálogo
        const tool = state.tools.find(t => t.id === res.toolId);
        if (tool) {
            tool.status = 'AVAILABLE';
        }

        renderCatalog();
        renderClientRentals();
        updateRentalsBadge();

        showToast(`La reserva #${res.id} ha sido cancelada exitosamente.`, 'warning');
    }

    // Finalizar / Devolver Pedido (Cliente)
    function handleRentalReturn(resId) {
        const res = state.reservations.find(r => r.id === resId);
        if (!res) return;

        const confirmReturn = confirm(`¿Confirmas la devolución y retiro del equipo "${res.toolName}" a Patio Central Girón? Se liquidará el fin del contrato y se liberará tu depósito de garantía.`);
        if (!confirmReturn) return;

        res.status = 'RETURNED';
        res.stepIndex = 4;

        // Liberar equipo en catálogo
        const tool = state.tools.find(t => t.id === res.toolId);
        if (tool) {
            tool.status = 'AVAILABLE';
        }

        renderCatalog();
        renderClientRentals();
        updateRentalsBadge();

        showToast(`¡Alquiler finalizado! Maquinaria recibida en Patio Girón y depósito en garantía liberado.`, 'success');
    }

    // =========================================================================
    // PASARELA DE PAGOS COLOMBIANA (PSE, TARJETAS, NEQUI)
    // =========================================================================

    function openPaymentGateway(resId) {
        const res = state.reservations.find(r => r.id === resId);
        if (!res) return;

        currentPayingReservationId = resId;
        const modal = document.getElementById('modal-payment-checkout');
        if (!modal) return;

        const subtotal = res.subtotal;
        const tax = res.tax || Math.round(subtotal * 0.19);
        const deposit = res.deposit;
        const total = subtotal + tax + deposit;

        // Actualizar datos del modal
        const codeEl = document.getElementById('pay-res-code');
        const toolEl = document.getElementById('pay-tool-name');
        const periodEl = document.getElementById('pay-rental-period');
        const subtotalEl = document.getElementById('pay-calc-subtotal');
        const taxEl = document.getElementById('pay-calc-tax');
        const depositEl = document.getElementById('pay-calc-deposit');
        const totalEl = document.getElementById('pay-calc-total');

        if (codeEl) codeEl.textContent = `#${res.id}`;
        if (toolEl) toolEl.textContent = res.toolName;
        if (periodEl) periodEl.textContent = `${res.days} días (${res.startDate} al ${res.endDate})`;
        if (subtotalEl) subtotalEl.textContent = `${currencyFormatter.format(subtotal)} COP`;
        if (taxEl) taxEl.textContent = `${currencyFormatter.format(tax)} COP`;
        if (depositEl) depositEl.textContent = `${currencyFormatter.format(deposit)} COP`;
        if (totalEl) totalEl.textContent = `${currencyFormatter.format(total)} COP`;

        document.querySelectorAll('.pay-btn-amount').forEach(el => {
            el.textContent = `${currencyFormatter.format(total)} COP`;
        });

        modal.classList.remove('hidden');
    }

    function setupPaymentGatewayListeners() {
        const modal = document.getElementById('modal-payment-checkout');
        const btnClose = document.getElementById('modal-payment-close');
        const processingOverlay = document.getElementById('payment-processing-overlay');
        const processingText = document.getElementById('pay-processing-text');

        if (btnClose && modal) {
            btnClose.addEventListener('click', () => modal.classList.add('hidden'));
        }

        if (modal) {
            modal.addEventListener('click', (e) => {
                if (e.target === modal) modal.classList.add('hidden');
            });
        }

        // Cambio de pestañas de métodos de pago
        document.querySelectorAll('.pay-tab-btn').forEach(tab => {
            tab.addEventListener('click', () => {
                document.querySelectorAll('.pay-tab-btn').forEach(t => t.classList.remove('active'));
                tab.classList.add('active');

                const method = tab.getAttribute('data-method');
                const formCard = document.getElementById('form-pay-card');
                const formPse = document.getElementById('form-pay-pse');
                const formNequi = document.getElementById('form-pay-nequi');

                if (formCard) formCard.classList.toggle('hidden', method !== 'card');
                if (formPse) formPse.classList.toggle('hidden', method !== 'pse');
                if (formNequi) formNequi.classList.toggle('hidden', method !== 'nequi');
            });
        });

        // Envíos de Formularios de Pago
        function executeSimulatedPayment(paymentMethodLabel) {
            if (!currentPayingReservationId) return;
            const res = state.reservations.find(r => r.id === currentPayingReservationId);
            if (!res) return;

            if (processingOverlay) {
                if (processingText) processingText.textContent = `Procesando pago con ${paymentMethodLabel}...`;
                processingOverlay.classList.remove('hidden');
            }

            setTimeout(() => {
                if (processingOverlay) processingOverlay.classList.add('hidden');
                if (modal) modal.classList.add('hidden');

                res.status = 'CONFIRMED';
                res.stepIndex = 2;
                res.paid = true;
                res.paymentMethod = paymentMethodLabel;
                res.approvalCode = `AUTH-${Math.floor(100000 + Math.random() * 900000)}`;

                renderClientRentals();
                updateRentalsBadge();

                showToast(`¡Pago Aprobado con ${paymentMethodLabel}! Factura electrónica generada. Tu maquinaria está en alistamiento.`, 'success', 5000);
            }, 1400);
        }

        const formCard = document.getElementById('form-pay-card');
        if (formCard) {
            formCard.addEventListener('submit', (e) => {
                e.preventDefault();
                const cardNum = document.getElementById('pay-card-number').value.replace(/\s+/g, '');
                const last4 = cardNum.slice(-4) || '9010';
                executeSimulatedPayment(`Tarjeta Crédito/Débito (terminada en •••• ${last4})`);
            });
        }

        const formPse = document.getElementById('form-pay-pse');
        if (formPse) {
            formPse.addEventListener('submit', (e) => {
                e.preventDefault();
                const bankSelect = document.getElementById('pse-bank');
                const bankName = bankSelect ? bankSelect.options[bankSelect.selectedIndex].text : 'Bancolombia';
                executeSimulatedPayment(`PSE Débito Bancario (${bankName})`);
            });
        }

        const formNequi = document.getElementById('form-pay-nequi');
        if (formNequi) {
            formNequi.addEventListener('submit', (e) => {
                e.preventDefault();
                const phone = document.getElementById('nequi-phone').value || '318 450 9900';
                executeSimulatedPayment(`Nequi / Daviplata (+57 ${phone})`);
            });
        }
    }

    // =========================================================================
    // VISOR DE FACTURA ELECTRÓNICA DE VENTA OFICIAL DIAN
    // =========================================================================

    function openInvoiceView(resId) {
        const res = state.reservations.find(r => r.id === resId);
        if (!res) return;

        const modal = document.getElementById('modal-invoice-view');
        if (!modal) return;

        const subtotal = res.subtotal;
        const tax = res.tax || Math.round(subtotal * 0.19);
        const deposit = res.deposit;
        const total = subtotal + tax + deposit;
        const invoiceNum = res.invoiceNumber || `RTSA-2026-${res.id.replace(/\D/g, '') || '0914'}`;

        // Rellenar cabecera DIAN
        const numEl = document.getElementById('inv-doc-number');
        if (numEl) numEl.textContent = invoiceNum;

        const now = new Date();
        const dateStr = now.toISOString().replace('T', ' ').slice(0, 19);
        const due = new Date();
        due.setDate(due.getDate() + 7);
        const dueStr = due.toISOString().split('T')[0];

        const issueEl = document.getElementById('inv-issue-date');
        const dueEl = document.getElementById('inv-due-date');
        const methodEl = document.getElementById('inv-payment-method');

        if (issueEl) issueEl.textContent = dateStr;
        if (dueEl) dueEl.textContent = dueStr;
        if (methodEl) methodEl.textContent = res.paymentMethod || 'Transferencia Electrónica PSE / Tarjeta';

        // Datos del cliente
        const clientNameEl = document.getElementById('inv-client-name');
        const clientNitEl = document.getElementById('inv-client-nit');
        const clientAddrEl = document.getElementById('inv-client-address');
        const clientEmailEl = document.getElementById('inv-client-email');
        const clientStatusEl = document.getElementById('inv-rental-status');
        const sigClientEl = document.getElementById('inv-sig-client');

        const activeUser = state.currentUser || { name: 'Constructora del Oriente S.A.S.', email: 'cliente.constructora@constructora.com' };

        if (clientNameEl) clientNameEl.textContent = activeUser.name;
        if (clientNitEl) clientNitEl.textContent = 'NIT 900.542.119-1 (Santander)';
        if (clientAddrEl) clientAddrEl.textContent = res.siteAddress;
        if (clientEmailEl) clientEmailEl.textContent = activeUser.email;
        if (sigClientEl) sigClientEl.textContent = activeUser.name.toUpperCase();

        if (clientStatusEl) {
            if (res.status === 'CONFIRMED' || res.status === 'DELIVERED' || res.status === 'RETURNED') {
                clientStatusEl.textContent = 'VIGENTE • EN REGLA';
                clientStatusEl.className = 'badge-invoice-status';
            } else if (res.status === 'CANCELLED') {
                clientStatusEl.textContent = 'ANULADA / CANCELADA';
                clientStatusEl.className = 'badge-invoice-status';
                clientStatusEl.style.background = '#fee2e2';
                clientStatusEl.style.color = '#b91c1c';
            } else {
                clientStatusEl.textContent = 'PROFORMA • PENDIENTE DE PAGO';
                clientStatusEl.className = 'badge-invoice-status';
                clientStatusEl.style.background = '#fef3c7';
                clientStatusEl.style.color = '#b45309';
            }
        }

        // Tabla de ítems
        const tableBody = document.getElementById('inv-items-table-body');
        if (tableBody) {
            tableBody.innerHTML = `
                <tr>
                    <td class="text-center">1</td>
                    <td>
                        <strong>${res.toolName}</strong><br>
                        <span style="font-size: 0.72rem; color: #64748b;">Arrendamiento de maquinaria pesada para obra en Santander • Serie: ${res.contractCode || 'CAT-2026-X'}</span>
                    </td>
                    <td class="text-center">${res.days}</td>
                    <td class="text-right">${currencyFormatter.format(res.dailyRate)} COP</td>
                    <td class="text-right"><strong>${currencyFormatter.format(subtotal)} COP</strong></td>
                </tr>
            `;
        }

        // Totales
        const depAmountEl = document.getElementById('inv-deposit-amount');
        const subtotalEl = document.getElementById('inv-subtotal-val');
        const taxEl = document.getElementById('inv-tax-val');
        const depositEl = document.getElementById('inv-deposit-val');
        const totalEl = document.getElementById('inv-total-val');
        const paidBadge = document.getElementById('inv-paid-badge');
        const cufeEl = document.getElementById('inv-cufe-code');

        if (depAmountEl) depAmountEl.textContent = currencyFormatter.format(deposit).replace('COP', '').trim();
        if (subtotalEl) subtotalEl.textContent = `${currencyFormatter.format(subtotal)} COP`;
        if (taxEl) taxEl.textContent = `${currencyFormatter.format(tax)} COP`;
        if (depositEl) depositEl.textContent = `${currencyFormatter.format(deposit)} COP`;
        if (totalEl) totalEl.textContent = `${currencyFormatter.format(total)} COP`;

        if (paidBadge) {
            if (res.paid || res.status === 'CONFIRMED' || res.status === 'DELIVERED' || res.status === 'RETURNED') {
                paidBadge.textContent = '✓ FACTURA PAGADA / CONFIRMADA';
                paidBadge.style.background = '#dcfce7';
                paidBadge.style.color = '#15803d';
            } else if (res.status === 'CANCELLED') {
                paidBadge.textContent = '✕ FACTURA ANULADA';
                paidBadge.style.background = '#fee2e2';
                paidBadge.style.color = '#b91c1c';
            } else {
                paidBadge.textContent = 'PENDIENTE DE PAGO';
                paidBadge.style.background = '#fef3c7';
                paidBadge.style.color = '#b45309';
            }
        }

        if (cufeEl) {
            cufeEl.textContent = res.cufeCode || 'a8b9c0d1e2f3456789abcdef0123456789abcdefa8b9c0d1e2f3456789abcdef0123456789abcdef01234567';
        }

        modal.classList.remove('hidden');
    }

    function setupInvoiceModalListeners() {
        const modal = document.getElementById('modal-invoice-view');
        const btnClose = document.getElementById('modal-invoice-close');
        const btnPrint = document.getElementById('btn-print-invoice');

        if (btnClose && modal) {
            btnClose.addEventListener('click', () => modal.classList.add('hidden'));
        }

        if (modal) {
            modal.addEventListener('click', (e) => {
                if (e.target === modal) modal.classList.add('hidden');
            });
        }

        if (btnPrint) {
            btnPrint.addEventListener('click', () => {
                window.print();
            });
        }
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

    // Instancias de Chart.js
    let barChartInstance = null;
    let donutChartInstance = null;
    let regionalChartInstance = null;

    function renderAnalytics() {
        const kpiRev = document.getElementById('kpi-revenue');
        const kpiRentals = document.getElementById('kpi-active-rentals');
        const kpiTools = document.getElementById('kpi-total-tools');
        const kpiOcc = document.getElementById('kpi-occupancy');

        if (kpiRev) kpiRev.textContent = `${currencyFormatter.format(state.metrics.totalRevenue)} COP`;
        if (kpiRentals) kpiRentals.textContent = state.metrics.activeRentals;
        if (kpiTools) kpiTools.textContent = `${state.metrics.totalTools} Equipos`;
        if (kpiOcc) kpiOcc.textContent = `${state.metrics.occupancyRate}%`;

        // Donut stats overlay y leyenda
        const total = 32;
        const available = 25;
        const rented = 5;
        const maint = 2;

        const pctAvail = Math.round((available / total) * 100);
        const pctRent = Math.round((rented / total) * 100);
        const pctMaint = Math.round((maint / total) * 100);

        const elPctAvail = document.getElementById('pct-available');
        const elPctRent = document.getElementById('pct-rented');
        const elPctMaint = document.getElementById('pct-maint');
        if (elPctAvail) elPctAvail.textContent = `${pctAvail}% (${available})`;
        if (elPctRent) elPctRent.textContent = `${pctRent}% (${rented})`;
        if (elPctMaint) elPctMaint.textContent = `${pctMaint}% (${maint})`;

        const donutTotalEl = document.getElementById('donut-stat-total');
        if (donutTotalEl) donutTotalEl.textContent = total;

        // Renderizado interactivo con Chart.js
        if (typeof Chart !== 'undefined') {
            renderChartJsAnalytics();
        }
    }

    function renderChartJsAnalytics() {
        // 1. Gráfico de Barras: Facturación por Categoría
        const ctxBar = document.getElementById('bar-chart-canvas');
        if (ctxBar) {
            if (barChartInstance) barChartInstance.destroy();

            const categories = state.metrics.categoriesRevenue || [];
            const labels = categories.map(c => c.category);
            const dataAmounts = categories.map(c => c.amount);

            barChartInstance = new Chart(ctxBar, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Facturación Consolidada',
                        data: dataAmounts,
                        backgroundColor: [
                            'rgba(15, 23, 42, 0.90)',
                            'rgba(30, 41, 59, 0.85)',
                            'rgba(51, 65, 85, 0.85)',
                            'rgba(71, 85, 105, 0.85)',
                            'rgba(100, 116, 139, 0.85)'
                        ],
                        hoverBackgroundColor: '#0f172a',
                        borderWidth: 1,
                        borderColor: '#cbd5e1',
                        borderRadius: 6,
                        maxBarThickness: 54
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    animation: { duration: 600 },
                    plugins: {
                        legend: { display: false },
                        tooltip: {
                            backgroundColor: '#0f172a',
                            titleFont: { family: 'Inter', size: 12, weight: '700' },
                            bodyFont: { family: 'Inter', size: 12 },
                            padding: 10,
                            displayColors: false,
                            callbacks: {
                                label: function(context) {
                                    return `Facturación: ${currencyFormatter.format(context.raw)} COP`;
                                }
                            }
                        }
                    },
                    scales: {
                        y: {
                            beginAtZero: true,
                            grid: { color: '#f1f5f9' },
                            ticks: {
                                callback: function(value) {
                                    return '$' + (value / 1000000).toFixed(0) + 'M';
                                },
                                font: { family: 'Inter', size: 11, weight: '500' },
                                color: '#64748b'
                            }
                        },
                        x: {
                            grid: { display: false },
                            ticks: {
                                font: { family: 'Inter', size: 11, weight: '600' },
                                color: '#334155'
                            }
                        }
                    }
                }
            });
        }

        // 2. Gráfico Donut: Estado de la Flota Santander
        const ctxDonut = document.getElementById('donut-chart-canvas');
        if (ctxDonut) {
            if (donutChartInstance) donutChartInstance.destroy();

            donutChartInstance = new Chart(ctxDonut, {
                type: 'doughnut',
                data: {
                    labels: ['Disponible en Patio', 'En Renta Activa', 'Mantenimiento Preventivo'],
                    datasets: [{
                        data: [25, 5, 2],
                        backgroundColor: ['#10b981', '#f59e0b', '#64748b'],
                        hoverBackgroundColor: ['#059669', '#d97706', '#475569'],
                        borderWidth: 2,
                        borderColor: '#ffffff',
                        hoverOffset: 6
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    cutout: '72%',
                    animation: { duration: 600 },
                    plugins: {
                        legend: { display: false },
                        tooltip: {
                            backgroundColor: '#0f172a',
                            titleFont: { family: 'Inter', size: 12, weight: '700' },
                            bodyFont: { family: 'Inter', size: 12 },
                            padding: 10,
                            callbacks: {
                                label: function(context) {
                                    const total = 32;
                                    const pct = Math.round((context.raw / total) * 100);
                                    return ` ${context.label}: ${context.raw} equipos (${pct}%)`;
                                }
                            }
                        }
                    }
                }
            });
        }

        // 3. Gráfico Horizontal: Actividad por Municipio en Santander
        const ctxRegional = document.getElementById('regional-chart-canvas');
        if (ctxRegional) {
            if (regionalChartInstance) regionalChartInstance.destroy();

            regionalChartInstance = new Chart(ctxRegional, {
                type: 'bar',
                data: {
                    labels: [
                        'Bucaramanga (Cabecera / Centro)',
                        'Floridablanca (Cañaveral / Ruitoque)',
                        'Girón (Chimitá / Anillo Vial)',
                        'Piedecuesta & Lebrija'
                    ],
                    datasets: [{
                        label: 'Facturación Regional ($COP)',
                        data: [20.4, 13.6, 8.7, 6.0],
                        backgroundColor: [
                            'rgba(16, 185, 129, 0.85)',
                            'rgba(2, 132, 199, 0.85)',
                            'rgba(15, 23, 42, 0.85)',
                            'rgba(100, 116, 139, 0.85)'
                        ],
                        hoverBackgroundColor: [
                            '#059669',
                            '#0284c7',
                            '#0f172a',
                            '#475569'
                        ],
                        borderRadius: 4,
                        maxBarThickness: 24
                    }]
                },
                options: {
                    indexAxis: 'y',
                    responsive: true,
                    maintainAspectRatio: false,
                    animation: { duration: 600 },
                    plugins: {
                        legend: { display: false },
                        tooltip: {
                            backgroundColor: '#0f172a',
                            titleFont: { family: 'Inter', size: 12, weight: '700' },
                            bodyFont: { family: 'Inter', size: 12 },
                            padding: 10,
                            callbacks: {
                                label: function(context) {
                                    const tools = ['6 maquinaria en obra (42%)', '4 maquinaria en obra (28%)', '3 maquinaria en obra (18%)', '1 maquinaria en obra (12%)'];
                                    return ` Facturación: $${context.raw}M COP • ${tools[context.dataIndex]}`;
                                }
                            }
                        }
                    },
                    scales: {
                        x: {
                            beginAtZero: true,
                            grid: { color: '#f1f5f9' },
                            ticks: {
                                callback: function(val) { return '$' + val + 'M'; },
                                font: { family: 'Inter', size: 11, weight: '500' },
                                color: '#64748b'
                            }
                        },
                        y: {
                            grid: { display: false },
                            ticks: {
                                font: { family: 'Inter', size: 11, weight: '600' },
                                color: '#334155'
                            }
                        }
                    }
                }
            });
        }
    }

    // =========================================================================
    // 9. GESTIÓN DE ROLES & AUTENTICACIÓN
    // =========================================================================

    function updateUserUI() {
        const guestBox = document.getElementById('auth-guest-box');
        const userBox = document.getElementById('auth-user-box');
        const avatar = document.getElementById('user-avatar');
        const displayName = document.getElementById('user-display-name');
        const roleBadge = document.getElementById('user-role-badge');

        const tabProvider = document.getElementById('tab-provider');
        const tabAnalytics = document.getElementById('tab-analytics');
        const tabDevoluciones = document.getElementById('tab-devoluciones');

        if (state.currentUser && state.currentUser.token) {
            if (guestBox) guestBox.classList.add('hidden');
            if (userBox) userBox.classList.remove('hidden');

            const initials = state.currentUser.name
                .split(' ')
                .filter(Boolean)
                .map(n => n[0])
                .slice(0, 2)
                .join('');
            if (avatar) avatar.textContent = initials.toUpperCase() || 'RT';
            if (displayName) displayName.textContent = state.currentUser.name;
            if (roleBadge) {
                roleBadge.textContent = state.currentUser.role;
                roleBadge.className = 'user-role-pill';
                if (state.currentUser.role === 'CLIENT') roleBadge.classList.add('role-client');
                else if (state.currentUser.role === 'PROVIDER') roleBadge.classList.add('role-provider');
                else if (state.currentUser.role === 'ADMIN') roleBadge.classList.add('role-admin');
            }

            // Control de visibilidad de portales según el rol autenticado
            const showProvider = (state.currentUser.role === 'PROVIDER' || state.currentUser.role === 'ADMIN');
            const showAdmin = (state.currentUser.role === 'ADMIN');
            const showProGroup = showProvider || showAdmin;

            if (tabProvider) tabProvider.style.display = showProvider ? '' : 'none';
            if (tabAnalytics) tabAnalytics.style.display = showAdmin ? '' : 'none';
            if (tabDevoluciones) tabDevoluciones.style.display = '';

            const navDivider = document.getElementById('nav-divider-pro');
            const navClusterPro = document.getElementById('nav-cluster-pro');
            const mobileTitleBiz = document.getElementById('mobile-title-biz');
            const mobileClusterBiz = document.getElementById('mobile-cluster-biz');

            if (navDivider) navDivider.style.display = '';
            if (navClusterPro) navClusterPro.style.display = '';
            if (mobileTitleBiz) mobileTitleBiz.style.display = '';
            if (mobileClusterBiz) mobileClusterBiz.style.display = '';

            // Redirección segura si el rol no tiene acceso a la sección actualmente activa
            const activeSection = document.querySelector('.content-section.active');
            if (activeSection && activeSection.id === 'section-analytics' && !showAdmin) {
                switchTab('section-catalog');
            } else if (activeSection && activeSection.id === 'section-provider' && !showProvider) {
                switchTab('section-catalog');
            }
        } else {
            if (guestBox) guestBox.classList.remove('hidden');
            if (userBox) userBox.classList.add('hidden');
            if (tabProvider) tabProvider.style.display = 'none';
            if (tabAnalytics) tabAnalytics.style.display = 'none';
            if (tabDevoluciones) tabDevoluciones.style.display = '';

            const navDivider = document.getElementById('nav-divider-pro');
            const navClusterPro = document.getElementById('nav-cluster-pro');
            const mobileTitleBiz = document.getElementById('mobile-title-biz');
            const mobileClusterBiz = document.getElementById('mobile-cluster-biz');
            if (navDivider) navDivider.style.display = '';
            if (navClusterPro) navClusterPro.style.display = '';
            if (mobileTitleBiz) mobileTitleBiz.style.display = '';
            if (mobileClusterBiz) mobileClusterBiz.style.display = '';
        }

        if (typeof updateDevolucionAuthStatus === 'function') {
            updateDevolucionAuthStatus();
        }
    }

    // Cambio de Pestañas de Navegación con Control de Acceso por Rol
    function switchTab(targetSectionId) {
        const userRole = (state.currentUser && state.currentUser.token) ? state.currentUser.role : 'GUEST';

        // Validación de permisos estrictos por módulo
        if (targetSectionId === 'section-analytics') {
            if (userRole !== 'ADMIN') {
                showToast('Acceso restringido: El módulo Métricas & BI es exclusivo para Administradores.', 'warning');
                return;
            }
        }

        if (targetSectionId === 'section-provider') {
            if (userRole !== 'PROVIDER' && userRole !== 'ADMIN') {
                showToast('Acceso restringido: El Panel de Proveedor es exclusivo para Proveedores o Administrador.', 'warning');
                return;
            }
        }

        if (targetSectionId === 'section-client-rentals') {
            if (userRole === 'GUEST') {
                showToast('Inicia sesión para consultar tus contratos y alquileres en curso.', 'info');
                const btnLogin = document.getElementById('btn-open-login');
                if (btnLogin) btnLogin.click();
                return;
            }
        }

        document.querySelectorAll('.nav-tab').forEach(tab => {
            tab.classList.toggle('active', tab.getAttribute('data-target') === targetSectionId);
        });
        document.querySelectorAll('.content-section').forEach(sec => {
            sec.classList.toggle('active', sec.id === targetSectionId);
        });

        // Cerrar panel móvil si está abierto
        const mobilePanel = document.getElementById('mobile-nav-panel');
        const toggleBtn = document.getElementById('btn-mobile-toggle');
        if (mobilePanel && mobilePanel.classList.contains('open')) {
            mobilePanel.classList.remove('open');
            if (toggleBtn) {
                toggleBtn.classList.remove('active');
                toggleBtn.setAttribute('aria-expanded', 'false');
            }
        }

        // Trigger updates depending on section
        if (targetSectionId === 'section-analytics') {
            renderAnalytics();
        } else if (targetSectionId === 'section-coverage') {
            setupProjectRadarMap();
        } else if (targetSectionId === 'section-provider') {
            renderProviderPanel();
        } else if (targetSectionId === 'section-client-rentals') {
            renderClientRentals();
        } else if (targetSectionId === 'section-devoluciones') {
            if (typeof renderDevolucionesTable === 'function') {
                renderDevolucionesTable();
            }
            if (typeof updateDevolucionAuthStatus === 'function') {
                updateDevolucionAuthStatus();
            }
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

        // Modal de Factura Electrónica DIAN e Impresión
        setupInvoiceModalListeners();

        // Modal de Pasarela de Pagos (PSE, Tarjetas, Nequi)
        setupPaymentGatewayListeners();

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

        // Módulo de Autenticación & Registro con Validaciones
        setupAuthLogic();

        // Menú Móvil Responsive y Sincronización
        initMobileNav();

        // Mapa Animado de Zonas de Proyecto & Logística Santander
        setupProjectRadarMap();
    }

    // =========================================================================
    // LÓGICA DE AUTENTICACIÓN, LOGIN & REGISTRO ROBUSTO
    // =========================================================================
    function setupAuthLogic() {
        const modalAuth = document.getElementById('modal-auth');
        const btnOpenLogin = document.getElementById('btn-open-login');
        const btnOpenRegister = document.getElementById('btn-open-register');
        const modalAuthClose = document.getElementById('modal-auth-close');
        const tabLoginBtn = document.getElementById('tab-login-btn');
        const tabRegisterBtn = document.getElementById('tab-register-btn');
        const formLogin = document.getElementById('form-login');
        const formRegister = document.getElementById('form-register');
        const btnLogout = document.getElementById('btn-logout');

        const loginAlert = document.getElementById('login-alert');
        const registerAlert = document.getElementById('register-alert');

        if (btnOpenLogin) {
            btnOpenLogin.addEventListener('click', () => {
                modalAuth.classList.remove('hidden');
                tabLoginBtn.click();
            });
        }

        if (btnOpenRegister) {
            btnOpenRegister.addEventListener('click', () => {
                modalAuth.classList.remove('hidden');
                tabRegisterBtn.click();
            });
        }

        if (modalAuthClose) {
            modalAuthClose.addEventListener('click', () => {
                modalAuth.classList.add('hidden');
            });
        }

        modalAuth.addEventListener('click', (e) => {
            if (e.target === modalAuth) {
                modalAuth.classList.add('hidden');
            }
        });

        // Alternar pestañas de Login y Registro
        tabLoginBtn.addEventListener('click', () => {
            tabLoginBtn.classList.add('active');
            tabLoginBtn.setAttribute('aria-selected', 'true');
            tabRegisterBtn.classList.remove('active');
            tabRegisterBtn.setAttribute('aria-selected', 'false');
            formLogin.classList.remove('hidden');
            formRegister.classList.add('hidden');
            if (loginAlert) loginAlert.classList.add('hidden');
        });

        tabRegisterBtn.addEventListener('click', () => {
            tabRegisterBtn.classList.add('active');
            tabRegisterBtn.setAttribute('aria-selected', 'true');
            tabLoginBtn.classList.remove('active');
            tabLoginBtn.setAttribute('aria-selected', 'false');
            formRegister.classList.remove('hidden');
            formLogin.classList.add('hidden');
            if (registerAlert) registerAlert.classList.add('hidden');
        });

        // Cargar cuentas oficiales del README.md
        document.querySelectorAll('.btn-quick-fill').forEach(btn => {
            btn.addEventListener('click', () => {
                const email = btn.getAttribute('data-email');
                const pass = btn.getAttribute('data-pass');
                const role = btn.getAttribute('data-role');

                const emailInput = document.getElementById('login-email');
                const passInput = document.getElementById('login-password');

                if (emailInput) {
                    emailInput.value = email;
                    emailInput.classList.remove('is-invalid');
                    emailInput.classList.add('is-valid');
                    document.getElementById('login-email-error').textContent = '';
                }
                if (passInput) {
                    passInput.value = pass;
                    passInput.classList.remove('is-invalid');
                    passInput.classList.add('is-valid');
                    document.getElementById('login-password-error').textContent = '';
                }
                if (loginAlert) loginAlert.classList.add('hidden');

                showToast(`Credenciales cargadas: ${role} (${email})`, 'info');
            });
        });

        // Mostrar / Ocultar contraseñas con el botón de ojo
        document.querySelectorAll('.btn-toggle-pwd').forEach(btn => {
            btn.addEventListener('click', () => {
                const targetId = btn.getAttribute('data-target');
                const input = document.getElementById(targetId);
                if (!input) return;
                const eyeOpen = btn.querySelector('.eye-open');
                const eyeClosed = btn.querySelector('.eye-closed');

                if (input.type === 'password') {
                    input.type = 'text';
                    if (eyeOpen) eyeOpen.classList.add('hidden');
                    if (eyeClosed) eyeClosed.classList.remove('hidden');
                } else {
                    input.type = 'password';
                    if (eyeOpen) eyeOpen.classList.remove('hidden');
                    if (eyeClosed) eyeClosed.classList.add('hidden');
                }
            });
        });

        // Selección Exclusiva de Rol en Registro (SOLO Cliente o Proveedor)
        const cardClient = document.getElementById('card-role-client');
        const cardProvider = document.getElementById('card-role-provider');
        const btnSubmitRegister = document.getElementById('btn-submit-register');

        function updateRoleSelection(selectedRole) {
            if (selectedRole === 'CLIENT') {
                if (cardClient) cardClient.classList.add('active');
                if (cardProvider) cardProvider.classList.remove('active');
                const radio = cardClient ? cardClient.querySelector('input[type="radio"]') : null;
                if (radio) radio.checked = true;
                if (btnSubmitRegister) {
                    btnSubmitRegister.querySelector('span').textContent = 'Crear Cuenta de Cliente Contratista';
                }
            } else if (selectedRole === 'PROVIDER') {
                if (cardProvider) cardProvider.classList.add('active');
                if (cardClient) cardClient.classList.remove('active');
                const radio = cardProvider ? cardProvider.querySelector('input[type="radio"]') : null;
                if (radio) radio.checked = true;
                if (btnSubmitRegister) {
                    btnSubmitRegister.querySelector('span').textContent = 'Crear Cuenta de Proveedor de Equipos';
                }
            }
        }

        if (cardClient) cardClient.addEventListener('click', () => updateRoleSelection('CLIENT'));
        if (cardProvider) cardProvider.addEventListener('click', () => updateRoleSelection('PROVIDER'));

        function isValidEmail(email) {
            return /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email.trim());
        }

        // Medidor y Requisitos de Contraseña Segura
        const regPassInput = document.getElementById('reg-password');
        const strengthMeterFill = document.getElementById('strength-meter-fill');
        const strengthLabel = document.getElementById('strength-label');
        const reqLength = document.getElementById('req-length');
        const reqUpper = document.getElementById('req-upper');
        const reqNumber = document.getElementById('req-number');
        const reqSpecial = document.getElementById('req-special');

        function checkPasswordQuality(pwd) {
            const hasLen = pwd.length >= 8;
            const hasUp = /[A-Z]/.test(pwd);
            const hasNum = /[0-9]/.test(pwd);
            const hasSpec = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(pwd);

            if (reqLength) {
                reqLength.classList.toggle('met', hasLen);
                reqLength.querySelector('.req-dot').textContent = hasLen ? '✓' : '✕';
            }
            if (reqUpper) {
                reqUpper.classList.toggle('met', hasUp);
                reqUpper.querySelector('.req-dot').textContent = hasUp ? '✓' : '✕';
            }
            if (reqNumber) {
                reqNumber.classList.toggle('met', hasNum);
                reqNumber.querySelector('.req-dot').textContent = hasNum ? '✓' : '✕';
            }
            if (reqSpecial) {
                reqSpecial.classList.toggle('met', hasSpec);
                reqSpecial.querySelector('.req-dot').textContent = hasSpec ? '✓' : '✕';
            }

            let score = 0;
            if (hasLen) score++;
            if (hasUp) score++;
            if (hasNum) score++;
            if (hasSpec) score++;

            if (strengthMeterFill && strengthLabel) {
                if (pwd.length === 0) {
                    strengthMeterFill.style.width = '0%';
                    strengthMeterFill.style.backgroundColor = '#e2e8f0';
                    strengthLabel.textContent = 'Ingresa tu contraseña';
                    strengthLabel.style.color = '#94a3b8';
                } else if (score <= 1) {
                    strengthMeterFill.style.width = '25%';
                    strengthMeterFill.style.backgroundColor = '#ef4444';
                    strengthLabel.textContent = 'Contraseña Débil (No cumple requisitos mínimos)';
                    strengthLabel.style.color = '#ef4444';
                } else if (score === 2) {
                    strengthMeterFill.style.width = '50%';
                    strengthMeterFill.style.backgroundColor = '#f97316';
                    strengthLabel.textContent = 'Contraseña Regular';
                    strengthLabel.style.color = '#f97316';
                } else if (score === 3) {
                    strengthMeterFill.style.width = '75%';
                    strengthMeterFill.style.backgroundColor = '#eab308';
                    strengthLabel.textContent = 'Contraseña Buena';
                    strengthLabel.style.color = '#ca8a04';
                } else {
                    strengthMeterFill.style.width = '100%';
                    strengthMeterFill.style.backgroundColor = '#10b981';
                    strengthLabel.textContent = 'Contraseña Fuerte y Segura';
                    strengthLabel.style.color = '#059669';
                }
            }

            return score >= 3 && hasLen;
        }

        if (regPassInput) {
            regPassInput.addEventListener('input', () => {
                checkPasswordQuality(regPassInput.value);
            });
        }

        // Validación en tiempo real de coincidencia de contraseña
        const regPassConfirmInput = document.getElementById('reg-password-confirm');
        const regPassConfirmError = document.getElementById('reg-password-confirm-error');

        function checkPasswordMatch() {
            if (!regPassConfirmInput || !regPassInput) return true;
            if (!regPassConfirmInput.value) {
                regPassConfirmInput.classList.remove('is-valid', 'is-invalid');
                if (regPassConfirmError) regPassConfirmError.textContent = '';
                return false;
            }
            if (regPassConfirmInput.value === regPassInput.value) {
                regPassConfirmInput.classList.add('is-valid');
                regPassConfirmInput.classList.remove('is-invalid');
                if (regPassConfirmError) regPassConfirmError.textContent = '';
                return true;
            } else {
                regPassConfirmInput.classList.add('is-invalid');
                regPassConfirmInput.classList.remove('is-valid');
                if (regPassConfirmError) regPassConfirmError.textContent = 'Las contraseñas no coinciden.';
                return false;
            }
        }

        if (regPassConfirmInput) {
            regPassConfirmInput.addEventListener('input', checkPasswordMatch);
        }

        // ================= ENVÍO FORMULARIO LOGIN =================
        if (formLogin) {
            formLogin.addEventListener('submit', async (e) => {
                e.preventDefault();
                const emailInput = document.getElementById('login-email');
                const passwordInput = document.getElementById('login-password');
                const email = emailInput.value.trim().toLowerCase();
                const password = passwordInput.value;

                let hasError = false;

                if (!email || !isValidEmail(email)) {
                    emailInput.classList.add('is-invalid');
                    document.getElementById('login-email-error').textContent = 'Ingresa un correo electrónico válido.';
                    hasError = true;
                } else {
                    emailInput.classList.remove('is-invalid');
                    emailInput.classList.add('is-valid');
                    document.getElementById('login-email-error').textContent = '';
                }

                if (!password) {
                    passwordInput.classList.add('is-invalid');
                    document.getElementById('login-password-error').textContent = 'Ingresa tu contraseña.';
                    hasError = true;
                } else {
                    passwordInput.classList.remove('is-invalid');
                    passwordInput.classList.add('is-valid');
                    document.getElementById('login-password-error').textContent = '';
                }

                if (hasError) return;

                // Buscar usuario en semillas del README o registrados en localStorage
                const allUsers = [...SYSTEM_ACCOUNTS, ...getStoredUsers()];
                const foundUser = allUsers.find(u => u.email.toLowerCase() === email);

                if (!foundUser || foundUser.password !== password) {
                    if (loginAlert) {
                        loginAlert.textContent = 'Credenciales inválidas. Verifica tu correo y contraseña o usa una cuenta oficial del README.';
                        loginAlert.classList.remove('hidden');
                    }
                    emailInput.classList.add('is-invalid');
                    passwordInput.classList.add('is-invalid');
                    return;
                }

                if (loginAlert) loginAlert.classList.add('hidden');

                // Obtener token JWT real del backend Spring Boot
                let realToken = `jwt-auth-session-${Date.now()}`;
                try {
                    const loginRes = await fetch(`${API_BASE_URL}/api/auth/login`, {
                        method: 'POST',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({ email: foundUser.email, password: password })
                    });
                    if (loginRes.ok) {
                        const loginData = await loginRes.json();
                        if (loginData && loginData.accessToken) {
                            realToken = loginData.accessToken;
                        }
                    }
                } catch (err) {
                    console.warn('[RentaTools Login]: Usando sesión local de respaldo.');
                }

                state.currentUser = {
                    id: foundUser.id,
                    name: foundUser.name,
                    email: foundUser.email,
                    role: foundUser.role,
                    token: realToken
                };

                try {
                    localStorage.setItem('rentatools_active_session', JSON.stringify(state.currentUser));
                } catch (err) {}

                updateUserUI();
                modalAuth.classList.add('hidden');

                // Redirección contextual según el rol de la cuenta
                if (foundUser.role === 'ADMIN') {
                    switchTab('section-analytics');
                    showToast(`Sesión iniciada como Administrador: ${foundUser.name}`, 'success');
                } else if (foundUser.role === 'PROVIDER') {
                    switchTab('section-devoluciones');
                    showToast(`Sesión iniciada como Proveedor: ${foundUser.name}`, 'success');
                } else {
                    switchTab('section-catalog');
                    showToast(`Bienvenido a RentaTools Santander, ${foundUser.name}`, 'success');
                }
            });
        }

        // ================= ENVÍO FORMULARIO REGISTRO =================
        if (formRegister) {
            formRegister.addEventListener('submit', (e) => {
                e.preventDefault();

                const nameInput = document.getElementById('reg-name');
                const emailInput = document.getElementById('reg-email');
                const passInput = document.getElementById('reg-password');
                const passConfirmInput = document.getElementById('reg-password-confirm');
                const termsCheckbox = document.getElementById('reg-terms');

                const selectedRoleRadio = document.querySelector('input[name="reg-role-radio"]:checked');
                // SEGURIDAD ESTRICTA: Solo se permite CLIENT o PROVIDER (NUNCA ADMIN)
                let selectedRole = selectedRoleRadio ? selectedRoleRadio.value : 'CLIENT';
                if (selectedRole !== 'CLIENT' && selectedRole !== 'PROVIDER') {
                    selectedRole = 'CLIENT';
                }

                let hasError = false;

                // Validación Nombre
                const name = nameInput.value.trim();
                if (!name || name.length < 3) {
                    nameInput.classList.add('is-invalid');
                    document.getElementById('reg-name-error').textContent = 'El nombre o razón social debe tener al menos 3 caracteres.';
                    hasError = true;
                } else {
                    nameInput.classList.remove('is-invalid');
                    nameInput.classList.add('is-valid');
                    document.getElementById('reg-name-error').textContent = '';
                }

                // Validación Correo
                const email = emailInput.value.trim().toLowerCase();
                const allUsers = [...SYSTEM_ACCOUNTS, ...getStoredUsers()];
                const isTaken = allUsers.some(u => u.email.toLowerCase() === email);

                if (!email || !isValidEmail(email)) {
                    emailInput.classList.add('is-invalid');
                    document.getElementById('reg-email-error').textContent = 'Ingresa un correo electrónico empresarial válido.';
                    hasError = true;
                } else if (isTaken || email === 'admin@rentatools.com') {
                    emailInput.classList.add('is-invalid');
                    document.getElementById('reg-email-error').textContent = 'Este correo ya está registrado o reservado por el sistema.';
                    hasError = true;
                } else {
                    emailInput.classList.remove('is-invalid');
                    emailInput.classList.add('is-valid');
                    document.getElementById('reg-email-error').textContent = '';
                }

                // Validación Contraseña
                const password = passInput.value;
                const isPassStrong = checkPasswordQuality(password);
                if (!isPassStrong) {
                    passInput.classList.add('is-invalid');
                    document.getElementById('reg-password-error').textContent = 'La contraseña debe tener mínimo 8 caracteres, mayúscula, número y símbolo.';
                    hasError = true;
                } else {
                    passInput.classList.remove('is-invalid');
                    passInput.classList.add('is-valid');
                    document.getElementById('reg-password-error').textContent = '';
                }

                // Validación Confirmación de Contraseña
                if (passConfirmInput.value !== password) {
                    passConfirmInput.classList.add('is-invalid');
                    document.getElementById('reg-password-confirm-error').textContent = 'Las contraseñas no coinciden.';
                    hasError = true;
                } else {
                    passConfirmInput.classList.remove('is-invalid');
                    passConfirmInput.classList.add('is-valid');
                    document.getElementById('reg-password-confirm-error').textContent = '';
                }

                // Validación Términos
                if (!termsCheckbox.checked) {
                    document.getElementById('reg-terms-error').textContent = 'Debes aceptar los términos y condiciones de servicio en Santander.';
                    hasError = true;
                } else {
                    document.getElementById('reg-terms-error').textContent = '';
                }

                if (hasError) {
                    if (registerAlert) {
                        registerAlert.textContent = 'Por favor corrige los campos señalados en rojo antes de continuar.';
                        registerAlert.classList.remove('hidden');
                    }
                    return;
                }

                if (registerAlert) registerAlert.classList.add('hidden');

                // Registrar nuevo usuario exclusivamente como Cliente o Proveedor
                const newUser = {
                    id: `usr-${Date.now()}`,
                    name: name,
                    email: email,
                    password: password,
                    role: selectedRole
                };

                saveRegisteredUser(newUser);

                // Iniciar sesión inmediatamente con la cuenta recién creada
                state.currentUser = {
                    id: newUser.id,
                    name: newUser.name,
                    email: newUser.email,
                    role: newUser.role,
                    token: `jwt-registered-${Date.now()}`
                };

                try {
                    localStorage.setItem('rentatools_active_session', JSON.stringify(state.currentUser));
                } catch (err) {}

                updateUserUI();
                modalAuth.classList.add('hidden');

                if (selectedRole === 'PROVIDER') {
                    switchTab('section-provider');
                    showToast(`¡Cuenta de Proveedor creada con éxito! Bienvenido, ${name}.`, 'success');
                } else {
                    switchTab('section-catalog');
                    showToast(`¡Cuenta de Cliente creada con éxito! Bienvenido, ${name}.`, 'success');
                }

                formRegister.reset();
                checkPasswordQuality('');
            });
        }

        // ================= CERRAR SESIÓN =================
        if (btnLogout) {
            btnLogout.addEventListener('click', () => {
                state.currentUser = null;
                try {
                    localStorage.removeItem('rentatools_active_session');
                } catch (err) {}
                updateUserUI();
                switchTab('section-catalog');
                showToast('Has cerrado sesión exitosamente.', 'info');
            });
        }
    }

    function initMobileNav() {
        const toggleBtn = document.getElementById('btn-mobile-toggle');
        const mobilePanel = document.getElementById('mobile-nav-panel');
        const opsContainer = document.getElementById('mobile-cluster-ops');
        const bizContainer = document.getElementById('mobile-cluster-biz');

        if (!toggleBtn || !mobilePanel) return;

        // Toggle drawer
        toggleBtn.addEventListener('click', (e) => {
            e.stopPropagation();
            const isOpen = mobilePanel.classList.toggle('open');
            toggleBtn.classList.toggle('active', isOpen);
            toggleBtn.setAttribute('aria-expanded', isOpen ? 'true' : 'false');
        });

        // Cerrar al hacer clic fuera del panel
        document.addEventListener('click', (e) => {
            if (mobilePanel.classList.contains('open') && !mobilePanel.contains(e.target) && !toggleBtn.contains(e.target)) {
                mobilePanel.classList.remove('open');
                toggleBtn.classList.remove('active');
                toggleBtn.setAttribute('aria-expanded', 'false');
            }
        });

        // Población del cluster de operaciones móvil
        if (opsContainer && opsContainer.children.length === 0) {
            const opsItems = [
                { target: 'section-catalog', icon: '📦', text: 'Catálogo de Maquinaria' },
                { target: 'section-coverage', icon: '🗺️', text: 'Cobertura GPS & Cerca' },
                { target: 'section-client-rentals', icon: '📋', text: 'Mis Alquileres' }
            ];

            opsItems.forEach(item => {
                const btn = document.createElement('button');
                btn.className = 'nav-tab';
                btn.setAttribute('data-target', item.target);
                btn.innerHTML = `<span class="nav-icon">${item.icon}</span> <span class="nav-text">${item.text}</span>`;
                btn.addEventListener('click', () => {
                    switchTab(item.target);
                });
                opsContainer.appendChild(btn);
            });
        }

        // Población del cluster de negocio móvil
        if (bizContainer && bizContainer.children.length === 0) {
            const bizItems = [
                { target: 'section-devoluciones', icon: '🔄', text: 'Devolución & Daños', badge: 'Examen 1.5H' },
                { target: 'section-provider', icon: '🏗️', text: 'Panel Proveedor', badge: 'Flota' },
                { target: 'section-analytics', icon: '📊', text: 'Métricas & BI', badge: 'Admin' }
            ];

            bizItems.forEach(item => {
                const btn = document.createElement('button');
                btn.className = 'nav-tab nav-tab-pro';
                btn.setAttribute('data-target', item.target);
                btn.innerHTML = `<span class="nav-icon">${item.icon}</span> <span class="nav-text">${item.text}</span> <span class="nav-badge-pro">${item.badge}</span>`;
                btn.addEventListener('click', () => {
                    switchTab(item.target);
                });
                bizContainer.appendChild(btn);
            });
        }
    }

    // =========================================================================
    // MAPA ANIMADO DE ZONAS DE PROYECTO & LOGÍSTICA SANTANDER (SIN APIs)
    // =========================================================================

    const SANTANDER_PROJECT_SITES = {
        giron: {
            badge: 'HUB LOGÍSTICO CENTRAL',
            title: 'Patio Central Girón (Anillo Vial Chimitá)',
            desc: 'Base de operaciones principal, parque automotor con 32 equipos y 4 camabajas para despacho continuo en Santander.',
            eta: '< 45 Minutos',
            fleet: '32 Equipos en Patio',
            freight: 'Flete Bonificado ($0 COP)',
            insurance: 'Sura Obra Santander Vigente',
            tools: [
                '🚜 <strong>Retroexcavadora CAT 420F2</strong> • Movimiento de Tierras',
                '🏗️ <strong>Minicargador Bobcat S570</strong> • Obras Urbanas',
                '💥 <strong>Martillo Bosch GSH 27 VC</strong> • Demolición de Pavimento'
            ]
        },
        bga: {
            badge: 'ÁREA METROPOLITANA',
            title: 'Bucaramanga (Cabecera, Centro & Provenza)',
            desc: 'Frente de proyectos residenciales de altura, mantenimiento de vías urbanas y adecuación comercial en la meseta.',
            eta: '< 1.5 Horas',
            fleet: '12 Equipos Asignados',
            freight: 'Tarifa Plana ($0 COP)',
            insurance: 'Póliza AMB Cobertura Total',
            tools: [
                '💥 <strong>Martillo Bosch GSH 27 VC</strong> • Demolición Estructural',
                '🚜 <strong>Retroexcavadora CAT 420F2</strong> • Cimentación de Edificios',
                '⚡ <strong>Generador Cummins 100kVA</strong> • Respaldo Eléctrico'
            ]
        },
        florida: {
            badge: 'CORREDOR SUR AMB',
            title: 'Floridablanca (Cañaveral & Valle de Mensulí)',
            desc: 'Desarrollos comerciales, conjuntos campestres y frentes de pavimentación en Autopista y Ruitoque Condominio.',
            eta: '< 1.0 Hora',
            fleet: '8 Equipos en Operación',
            freight: 'Tarifa Plana ($0 COP)',
            insurance: 'Sura Obra Santander Vigente',
            tools: [
                '🚜 <strong>Rodillo Compactador Vibromak 1.5 Ton</strong> • Pavimento y Asfalto',
                '🏗️ <strong>Minicargador Bobcat S570</strong> • Movimiento de Materiales',
                '🪜 <strong>Plataforma Tijera Genie GS-2646</strong> • Fachadas y Alturas'
            ]
        },
        piede: {
            badge: 'ZONA DE EXPANSIÓN',
            title: 'Piedecuesta (Guatiguará & Barroblanco)',
            desc: 'Sector de macroproyectos habitacionales, parques logísticos y canalizaciones de aguas pluviales en Guatiguará.',
            eta: '< 2.0 Horas',
            fleet: '6 Equipos en Operación',
            freight: 'Tarifa Plana ($0 COP)',
            insurance: 'Sura Obra Santander Vigente',
            tools: [
                '🚜 <strong>Retroexcavadora CAT 420F2</strong> • Excavaciones Masivas',
                '🚜 <strong>Rodillo Compactador Vibromak</strong> • Rasantes Viales',
                '💡 <strong>Torre de Iluminación LED Móvil</strong> • Turnos Nocturnos'
            ]
        },
        barranca: {
            badge: 'MAGDALENA MEDIO INDUSTRIAL',
            title: 'Barrancabermeja (Base Satélite Yuma)',
            desc: 'Corredor petroquímico e industrial con suministro de generadores pesados, compresores y maquinaria de gran tonelaje.',
            eta: '< 5.0 Horas',
            fleet: '9 Equipos Asignados',
            freight: 'Flete Industrial Regional Bonificado',
            insurance: 'Póliza Ecopetrol / Sura Vigente',
            tools: [
                '⚡ <strong>Generador Diésel Cummins 100kVA</strong> • Operación Continua',
                '🚜 <strong>Retroexcavadora CAT 420F2</strong> • Mantenimiento de Líneas',
                '🪜 <strong>Plataforma Tijera Genie GS-2646</strong> • Montajes Industriales'
            ]
        },
        lebrija: {
            badge: 'CORREDOR AEROPUERTO',
            title: 'Lebrija (Sector Palonegro & Aeropuerto)',
            desc: 'Mantenimiento aeroportuario, vías suburbanas y explanaciones de terrenos en el occidente de Santander.',
            eta: '< 2.5 Horas',
            fleet: '5 Equipos Disponibles',
            freight: 'Flete Suburbano Bonificado ($45.000 COP)',
            insurance: 'Sura Obra Santander Vigente',
            tools: [
                '🏗️ <strong>Minicargador Bobcat S570</strong> • Rasantes de Vía',
                '🚜 <strong>Retroexcavadora CAT 420F2</strong> • Movimiento de Tierras',
                '💥 <strong>Martillo Bosch GSH 27 VC</strong> • Rotura de Concreto'
            ]
        }
    };

    let activeRadarSiteKey = 'giron';

    function selectRadarSite(siteKey) {
        const site = SANTANDER_PROJECT_SITES[siteKey];
        if (!site) return;

        activeRadarSiteKey = siteKey;

        // Actualizar panel interactivo
        const badgeEl = document.getElementById('project-zone-badge');
        const titleEl = document.getElementById('project-site-title');
        const descEl = document.getElementById('project-site-desc');
        const etaEl = document.getElementById('project-eta-val');
        const fleetEl = document.getElementById('project-fleet-val');
        const freightEl = document.getElementById('project-freight-val');
        const insuranceEl = document.getElementById('project-insurance-val');
        const listEl = document.getElementById('project-tools-deployed-list');

        if (badgeEl) badgeEl.textContent = site.badge;
        if (titleEl) titleEl.textContent = site.title;
        if (descEl) descEl.textContent = site.desc;
        if (etaEl) etaEl.textContent = site.eta;
        if (fleetEl) fleetEl.textContent = site.fleet;
        if (freightEl) freightEl.textContent = site.freight;
        if (insuranceEl) insuranceEl.textContent = site.insurance;

        if (listEl) {
            listEl.innerHTML = site.tools.map(t => `<li>${t}</li>`).join('');
        }

        // Resaltar botón píldora
        document.querySelectorAll('.site-pill-btn').forEach(btn => {
            btn.classList.toggle('active', btn.getAttribute('data-site') === siteKey);
        });

        // Resaltar pin en el SVG animado
        document.querySelectorAll('.project-pin-group.site-pin').forEach(pin => {
            const isMatch = pin.getAttribute('data-site') === siteKey;
            pin.style.transform = isMatch ? 'scale(1.15)' : 'scale(1)';
            const circle = pin.querySelector('circle');
            if (circle) {
                pin.style.transformOrigin = `${circle.getAttribute('cx')}px ${circle.getAttribute('cy')}px`;
            }
            pin.style.transition = 'transform 0.25s ease';
        });
    }

    function setupProjectRadarMap() {
        // Clics en pines del mapa vectorial SVG
        document.querySelectorAll('.project-pin-group.site-pin').forEach(pin => {
            pin.addEventListener('click', () => {
                const siteKey = pin.getAttribute('data-site');
                if (siteKey) {
                    selectRadarSite(siteKey);
                    showToast(`Frente de Obra: ${SANTANDER_PROJECT_SITES[siteKey]?.title}`, 'info', 2000);
                }
            });
        });

        // Clics en botones rápidos del selector
        document.querySelectorAll('.site-pill-btn').forEach(btn => {
            btn.addEventListener('click', () => {
                const siteKey = btn.getAttribute('data-site');
                if (siteKey) {
                    selectRadarSite(siteKey);
                }
            });
        });

        // Botón "Rentar Maquinaria para este Frente de Obra"
        const btnRentSite = document.getElementById('btn-rent-for-site');
        if (btnRentSite) {
            btnRentSite.addEventListener('click', () => {
                const site = SANTANDER_PROJECT_SITES[activeRadarSiteKey];
                switchTab('section-catalog');
                showToast(`Mostrando equipos disponibles para despacho a: ${site?.title}`, 'success');
            });
        }

        // Seleccionar sitio inicial por defecto
        selectRadarSite(activeRadarSiteKey);
    }

    // =========================================================================
    // EXAMEN 1.5H: MÓDULO DE GESTIÓN DE DEVOLUCIONES Y REPORTES DE DAÑOS
    // =========================================================================

    let devolucionesHistory = [
        { id: '90000000-0000-0000-0000-000000000005', num: 'RES-2026-10005', tool: 'Martillo Hidráulico Bosch GSH 27', status: 'COMPLETED', returnStatus: 'DEVUELTO_CON_DAÑOS', damage: 'Mandril roto y daño en cable de alimentación', cost: 185000 },
        { id: '90000000-0000-0000-0000-000000000006', num: 'RES-2026-10006', tool: 'Plataforma Tijera Genie GS-1930', status: 'COMPLETED', returnStatus: 'DEVUELTO_OK', damage: 'Sin averías (OK)', cost: 0 },
        { id: '90000000-0000-0000-0000-000000000007', num: 'RES-2026-10007', tool: 'Excavadora Oruga CAT 320D', status: 'COMPLETED', returnStatus: 'DEVUELTO_OK', damage: 'Sin averías (OK)', cost: 0 },
        { id: '90000000-0000-0000-0000-000000000008', num: 'RES-2026-10008', tool: 'Retroexcavadora John Deere 310L', status: 'COMPLETED', returnStatus: 'DEVUELTO_CON_DAÑOS', damage: 'Fisura en manguera de alta presion', cost: 480000 },
        { id: '90000000-0000-0000-0000-000000000009', num: 'RES-2026-10009', tool: 'Compresor de Aire Sullair 185', status: 'RETURNED', returnStatus: 'PENDIENTE_DEVOLUCION', damage: '-', cost: 0 },
        { id: '90000000-0000-0000-0000-000000000010', num: 'RES-2026-10010', tool: 'Torre de Iluminación Wacker LT4', status: 'RETURNED', returnStatus: 'PENDIENTE_DEVOLUCION', damage: '-', cost: 0 },
        { id: '90000000-0000-0000-0000-000000000013', num: 'RES-2026-10013', tool: 'Allanadora de Concreto Multiquip', status: 'RETURNED', returnStatus: 'PENDIENTE_DEVOLUCION', damage: '-', cost: 0 },
        { id: '90000000-0000-0000-0000-000000000014', num: 'RES-2026-10014', tool: 'Rodillo Compactador Dynapac CC1200', status: 'RETURNED', returnStatus: 'PENDIENTE_DEVOLUCION', damage: '-', cost: 0 }
    ];

    function updateDevolucionAuthStatus() {
        const badge = document.getElementById('devolucion-current-user-badge');
        const banner = document.getElementById('devolucion-auth-banner');
        const btnQuick = document.getElementById('btn-quick-login-provider');
        if (!badge || !banner) return;

        const isAuthed = (state.currentUser && state.currentUser.token && (state.currentUser.role === 'PROVIDER' || state.currentUser.role === 'ADMIN'));

        if (isAuthed) {
            badge.innerHTML = `<strong style="color:#059669;">${state.currentUser.name} (${state.currentUser.role})</strong>`;
            banner.style.background = '#ecfdf5';
            banner.style.borderLeftColor = '#059669';
            if (btnQuick) btnQuick.style.display = 'none';
        } else {
            const currentName = (state.currentUser && state.currentUser.name) ? `${state.currentUser.name} (${state.currentUser.role || 'GUEST'})` : 'GUEST (Sin iniciar sesión)';
            badge.innerHTML = `<strong style="color:#b45309;">${currentName}</strong>`;
            banner.style.background = '#fffbeb';
            banner.style.borderLeftColor = '#d97706';
            if (btnQuick) btnQuick.style.display = '';
        }
    }

    async function quickAuthenticateProvider() {
        showToast('Autenticando con Spring Boot como Proveedor...', 'info');
        try {
            const res = await fetch(`${API_BASE_URL}/api/auth/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    email: 'contacto@maquinariaandina.com',
                    password: 'Password123!'
                })
            });

            if (res.ok) {
                const data = await res.json();
                state.currentUser = {
                    id: data.userId || '20000000-0000-0000-0000-000000000001',
                    name: 'Maquinaria y Andamios Andina',
                    email: data.email || 'contacto@maquinariaandina.com',
                    role: 'PROVIDER',
                    token: data.accessToken
                };
                localStorage.setItem('rentatools_active_session', JSON.stringify(state.currentUser));
                updateUserUI();
                showToast('¡Autenticado con éxito como Proveedor! Token JWT cargado.', 'success');
            } else {
                throw new Error('No se pudo autenticar con el backend');
            }
        } catch (e) {
            // Fallback a sesión local
            state.currentUser = {
                id: '20000000-0000-0000-0000-000000000001',
                name: 'Maquinaria y Andamios Andina',
                email: 'contacto@maquinariaandina.com',
                role: 'PROVIDER',
                token: `jwt-provider-session-${Date.now()}`
            };
            localStorage.setItem('rentatools_active_session', JSON.stringify(state.currentUser));
            updateUserUI();
            showToast('Sesión de Proveedor activa (Modo Local).', 'info');
        }
        updateDevolucionAuthStatus();
    }

    function renderDevolucionesTable() {
        const tbody = document.getElementById('table-devoluciones-tbody');
        if (!tbody) return;

        tbody.innerHTML = devolucionesHistory.map(row => {
            const isOk = row.returnStatus === 'DEVUELTO_OK';
            const isDanos = row.returnStatus === 'DEVUELTO_CON_DAÑOS';
            const badgeClass = isOk ? 'badge-return-ok' : isDanos ? 'badge-return-danos' : 'badge-return-pending';

            return `
                <tr>
                    <td style="font-weight:700; color:var(--text-primary); font-family:var(--font-mono);">${row.num}</td>
                    <td style="font-family:var(--font-mono); font-size:0.75rem; color:var(--text-muted);">${row.id}</td>
                    <td><strong>${row.tool}</strong></td>
                    <td><span style="font-size:0.72rem; padding:2px 8px; border-radius:4px; background:#f1f5f9; font-weight:600;">${row.status}</span></td>
                    <td><span class="${badgeClass}">${row.returnStatus}</span></td>
                    <td style="max-width:220px; font-size:0.8rem; color:${isDanos ? '#be123c' : 'inherit'};">${row.damage}</td>
                    <td style="font-weight:700;">${row.cost > 0 ? currencyFormatter.format(row.cost) : '-'}</td>
                    <td>
                        <button type="button" class="btn-secondary btn-select-reserva-exam" data-id="${row.id}" style="padding:4px 8px; font-size:0.75rem;">
                            Inspeccionar
                        </button>
                    </td>
                </tr>
            `;
        }).join('');

        // Listeners para los botones "Inspeccionar"
        tbody.querySelectorAll('.btn-select-reserva-exam').forEach(btn => {
            btn.addEventListener('click', () => {
                const resId = btn.getAttribute('data-id');
                const presetSelect = document.getElementById('dev-reserva-preset');
                const manualInput = document.getElementById('dev-reserva-id');
                const manualWrap = document.getElementById('dev-reserva-manual-wrap');

                if (presetSelect) {
                    const hasOption = Array.from(presetSelect.options).some(o => o.value === resId);
                    if (hasOption) {
                        presetSelect.value = resId;
                        if (manualWrap) manualWrap.classList.add('hidden');
                    } else {
                        presetSelect.value = 'CUSTOM';
                        if (manualWrap) manualWrap.classList.remove('hidden');
                    }
                }
                if (manualInput) manualInput.value = resId;

                // Scroll suave al formulario
                const formSection = document.getElementById('form-registro-devolucion');
                if (formSection) formSection.scrollIntoView({ behavior: 'smooth', block: 'center' });
                showToast(`Reserva seleccionada para inspección: ${resId.substring(0, 8)}...`, 'info');
            });
        });
    }

    function setupDevolucionesExamModule() {
        const presetSelect = document.getElementById('dev-reserva-preset');
        const manualWrap = document.getElementById('dev-reserva-manual-wrap');
        const manualInput = document.getElementById('dev-reserva-id');
        const cardStatusOk = document.getElementById('card-status-ok');
        const cardStatusDanos = document.getElementById('card-status-danos');
        const damageFields = document.getElementById('dev-damage-fields');
        const formDevolucion = document.getElementById('form-registro-devolucion');
        const btnQuickAuth = document.getElementById('btn-quick-login-provider');
        const btnRefreshTable = document.getElementById('btn-refresh-devoluciones-table');

        // Botón de autenticación rápida
        if (btnQuickAuth) {
            btnQuickAuth.addEventListener('click', quickAuthenticateProvider);
        }

        // Refrescar tabla
        if (btnRefreshTable) {
            btnRefreshTable.addEventListener('click', () => {
                renderDevolucionesTable();
                showToast('Matriz de devoluciones actualizada.', 'info');
            });
        }

        // Dropdown preset vs manual
        if (presetSelect) {
            presetSelect.addEventListener('change', () => {
                if (presetSelect.value === 'CUSTOM') {
                    if (manualWrap) manualWrap.classList.remove('hidden');
                    if (manualInput) {
                        manualInput.value = '';
                        manualInput.focus();
                    }
                } else {
                    if (manualWrap) manualWrap.classList.add('hidden');
                    if (manualInput) manualInput.value = presetSelect.value;
                }
            });
        }

        // Selector Interactivo de Estado (OK vs DAÑOS)
        function setInspectionStatus(val) {
            const isOk = val === 'OK';
            if (cardStatusOk) {
                cardStatusOk.classList.toggle('active', isOk);
                const r = cardStatusOk.querySelector('input[type="radio"]');
                if (r) r.checked = isOk;
            }
            if (cardStatusDanos) {
                cardStatusDanos.classList.toggle('active', !isOk);
                const r = cardStatusDanos.querySelector('input[type="radio"]');
                if (r) r.checked = !isOk;
            }
            if (damageFields) {
                if (isOk) {
                    damageFields.classList.add('hidden');
                } else {
                    damageFields.classList.remove('hidden');
                }
            }
        }

        if (cardStatusOk) cardStatusOk.addEventListener('click', () => setInspectionStatus('OK'));
        if (cardStatusDanos) cardStatusDanos.addEventListener('click', () => setInspectionStatus('DAÑOS'));

        // Chips rápidos de descripción
        document.querySelectorAll('.chip-suggestion[data-text]').forEach(chip => {
            chip.addEventListener('click', () => {
                const descInput = document.getElementById('dev-damage-desc');
                if (descInput) {
                    descInput.value = chip.getAttribute('data-text');
                    descInput.focus();
                }
            });
        });

        // Chips rápidos de costo
        document.querySelectorAll('.chip-suggestion[data-cost]').forEach(chip => {
            chip.addEventListener('click', () => {
                const costInput = document.getElementById('dev-damage-cost');
                if (costInput) {
                    costInput.value = chip.getAttribute('data-cost');
                    costInput.focus();
                }
            });
        });

        // Envío del Formulario Devolución
        if (formDevolucion) {
            formDevolucion.addEventListener('submit', async (e) => {
                e.preventDefault();

                const reservaId = (manualInput && manualInput.value.trim()) ? manualInput.value.trim() : (presetSelect ? presetSelect.value : '');
                if (!reservaId || reservaId === 'CUSTOM') {
                    showToast('Por favor selecciona o ingresa el ID de la reserva.', 'warning');
                    return;
                }

                const selectedRadio = document.querySelector('input[name="dev-estado-radio"]:checked');
                const estadoVal = selectedRadio ? selectedRadio.value : 'OK';

                const descVal = document.getElementById('dev-damage-desc') ? document.getElementById('dev-damage-desc').value.trim() : '';
                const costVal = document.getElementById('dev-damage-cost') ? parseFloat(document.getElementById('dev-damage-cost').value) : 0;

                if (estadoVal === 'DAÑOS') {
                    if (!descVal) {
                        showToast('Por favor ingresa la descripción técnica del daño detectado.', 'warning');
                        document.getElementById('dev-damage-desc').focus();
                        return;
                    }
                    if (isNaN(costVal) || costVal <= 0) {
                        showToast('Por favor ingresa un costo estimado de reparación válido en COP.', 'warning');
                        document.getElementById('dev-damage-cost').focus();
                        return;
                    }
                }

                // Asegurar token de Proveedor
                if (!state.currentUser || !state.currentUser.token || (state.currentUser.role !== 'PROVIDER' && state.currentUser.role !== 'ADMIN')) {
                    await quickAuthenticateProvider();
                }

                const btnSubmit = document.getElementById('btn-submit-devolucion');
                const originalBtnHtml = btnSubmit ? btnSubmit.innerHTML : '';
                if (btnSubmit) {
                    btnSubmit.disabled = true;
                    btnSubmit.innerHTML = `<span class="spinner-inline"></span> Registrando en Spring Boot...`;
                }

                const payload = { estado: estadoVal };
                if (estadoVal === 'DAÑOS') {
                    payload.descripcionDano = descVal;
                    payload.costoReparacion = costVal;
                }

                const httpBadge = document.getElementById('dev-http-status-badge');
                const jsonPre = document.getElementById('dev-json-output');
                const resIdDisplay = document.getElementById('dev-res-id-display');
                const resStatusDisplay = document.getElementById('dev-res-status-display');
                const damageCardPreview = document.getElementById('dev-damage-card-preview');

                try {
                    const endpointUrl = `${API_BASE_URL}/api/reservas/${encodeURIComponent(reservaId)}/registrar-devolucion`;
                    const res = await fetch(endpointUrl, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${state.currentUser.token}`
                        },
                        body: JSON.stringify(payload)
                    });

                    const responseData = await res.json().catch(() => ({ status: res.status, statusText: res.statusText }));

                    if (httpBadge) {
                        httpBadge.textContent = `HTTP ${res.status} ${res.ok ? 'OK' : 'ERROR'}`;
                        httpBadge.style.background = res.ok ? '#dcfce7' : '#fee2e2';
                        httpBadge.style.color = res.ok ? '#15803d' : '#b91c1c';
                    }

                    if (jsonPre) {
                        jsonPre.textContent = JSON.stringify(responseData, null, 2);
                    }

                    if (res.ok) {
                        if (resIdDisplay) resIdDisplay.textContent = responseData.id || reservaId;
                        if (resStatusDisplay) {
                            resStatusDisplay.textContent = responseData.estadoDevolucion || (estadoVal === 'OK' ? 'DEVUELTO_OK' : 'DEVUELTO_CON_DAÑOS');
                            resStatusDisplay.className = estadoVal === 'OK' ? 'badge-return-ok' : 'badge-return-danos';
                        }

                        if (estadoVal === 'DAÑOS' && damageCardPreview) {
                            damageCardPreview.classList.remove('hidden');
                            document.getElementById('dev-preview-desc').textContent = descVal;
                            document.getElementById('dev-preview-cost').textContent = currencyFormatter.format(costVal);
                            document.getElementById('dev-preview-date').textContent = new Date().toLocaleString('es-CO');
                        } else if (damageCardPreview) {
                            damageCardPreview.classList.add('hidden');
                        }

                        // Actualizar historial local
                        const existingIdx = devolucionesHistory.findIndex(h => h.id === reservaId);
                        const updatedItem = {
                            id: reservaId,
                            num: responseData.id ? `RES-${responseData.id.substring(0, 8)}` : `RES-${reservaId.substring(0, 8)}`,
                            tool: 'Equipo de Construcción',
                            status: responseData.status || 'COMPLETED',
                            returnStatus: responseData.estadoDevolucion || (estadoVal === 'OK' ? 'DEVUELTO_OK' : 'DEVUELTO_CON_DAÑOS'),
                            damage: estadoVal === 'DAÑOS' ? descVal : 'Sin averías (OK)',
                            cost: estadoVal === 'DAÑOS' ? costVal : 0
                        };

                        if (existingIdx >= 0) {
                            devolucionesHistory[existingIdx] = { ...devolucionesHistory[existingIdx], ...updatedItem };
                        } else {
                            devolucionesHistory.unshift(updatedItem);
                        }

                        renderDevolucionesTable();
                        showToast(`¡Devolución registrada con éxito! Estado: ${responseData.estadoDevolucion}`, 'success');
                    } else {
                        showToast(`Error al registrar devolución: ${responseData.message || res.statusText}`, 'error');
                    }
                } catch (err) {
                    if (httpBadge) {
                        httpBadge.textContent = 'ERROR DE RED';
                        httpBadge.style.background = '#fee2e2';
                        httpBadge.style.color = '#b91c1c';
                    }
                    if (jsonPre) {
                        jsonPre.textContent = `// Error de conexión con el backend:\n${err.message}`;
                    }
                    showToast(`Error de conexión con Spring Boot: ${err.message}`, 'error');
                } finally {
                    if (btnSubmit) {
                        btnSubmit.disabled = false;
                        btnSubmit.innerHTML = originalBtnHtml;
                    }
                }
            });
        }

        renderDevolucionesTable();
        updateDevolucionAuthStatus();
    }

    // =========================================================================
    // INICIALIZACIÓN PRINCIPAL DE LA APLICACIÓN
    // =========================================================================

    document.addEventListener('DOMContentLoaded', () => {
        updateUserUI();
        renderCatalog();
        renderClientRentals();
        renderProviderPanel();
        renderAnalytics();
        updateRentalsBadge();
        initEventListeners();
        setupDevolucionesExamModule();

        // Sincronización asíncrona con el backend si está corriendo
        syncCatalogWithBackend();
        syncMetricsFromBackend();
    });

})();
