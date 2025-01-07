// Initialisation de la carte OpenStreetMap
var map = L.map('map').setView([48.8566, 2.3522], 150); // Paris par défaut

// Ajouter les tuiles OpenStreetMap
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors',
    maxZoom: 19
}).addTo(map);



// Fonction pour charger les localisations des bus depuis l'API
function loadBusLocations() {
    // Appel API pour récupérer les positions des bus
    fetch('http://localhost:8092/api/bus-locations')
        .then(response => response.json())
        .then(data => {
            // Supprimer les anciens marqueurs (optionnel si actualisation en temps réel)
            map.eachLayer(function (layer) {
                if (layer instanceof L.Marker) {
                    map.removeLayer(layer);
                }
            });

            // Ajouter un marqueur pour chaque bus
            data.forEach(bus => {
                L.marker([bus.latitude, bus.longitude])
                    .addTo(map)
                    .bindPopup(`<b>Bus ID:</b> ${bus.busId}`)
                    .openPopup();
            });
        })
        .catch(error => console.error('Erreur lors du chargement des localisations des bus:', error));
}

// Charger les localisations dès que la page est prête
loadBusLocations();

// Actualiser les données toutes les 5 secondes
setInterval(() => {
    loadBusLocations();
}, 5000);
