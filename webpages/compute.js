let chart;
const backendPort = 8080; // match Main.java fixed port

function compute() {
    const nInput = document.getElementById('n');
    if (!nInput) return alert("Input element not found!");
    const n = nInput.value;
    if (!n || isNaN(n)) return alert("Please enter a valid number");

    fetch(`http://127.0.0.1:${backendPort}/quick?n=${n}`)
        .then(response => {
            if (!response.ok) throw new Error(`HTTP error: ${response.status}`);
            return response.json();
        })
        .then(data => {
            console.log("Received from backend:", data);

            if (chart) chart.destroy();

            const ctx = document.getElementById('chart').getContext('2d');
            chart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['Digit Factorial Sum'],
                    datasets: [{
                        label: `Digit Factorial Sum of ${n}`,
                        data: data,
                        backgroundColor: 'rgba(54,162,235,0.6)',
                        borderColor: 'rgba(54,162,235,1)',
                        borderWidth: 1
                    }]
                },
                options: { 
                    responsive: true, 
                    scales: { y: { beginAtZero: true } } 
                }
            });
        })
        .catch(err => {
            console.error("Error fetching data:", err);
            alert("Failed to fetch data from backend. Check console.");
        });
}
