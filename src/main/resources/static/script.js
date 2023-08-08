fetch('/api/v1/products')
    .then(response => response.json())
    .then(data => {
        const productTableBody = document.getElementById('productTableBody');
        const rows = []; // Define the 'rows' array to store row elements

        data.forEach(product => {
            const row = productTableBody.insertRow();
            const nameCell = row.insertCell(0);
            const priceCell = row.insertCell(1);
            const creatorCell = row.insertCell(2);
            const typeCell = row.insertCell(3);

            nameCell.textContent = product.name;
            priceCell.textContent = `$${product.price.toFixed(2)}`;
            creatorCell.textContent = product.creator;
            typeCell.textContent = product.type;

            // Add the row to the 'rows' array
            rows.push(row);

            row.addEventListener('click', () => {
                // Remove highlight from previously selected rows
                rows.forEach(r => r.classList.remove('selected'));

                // Add highlight to the clicked row
                row.classList.add('selected');

                // Retrieve data from the selected row
                const name = row.cells[0].textContent;
                const price = row.cells[1].textContent;
                const creator = row.cells[2].textContent;

                console.log(`Selected: ${name} | ${price} | ${creator}`);
            });
        });
    })
    .catch(error => console.error('Error fetching data:', error));
