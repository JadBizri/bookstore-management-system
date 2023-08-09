const productTableBody = document.getElementById('productTableBody');
const productsSelect = document.getElementById('products');
const sortSelect = document.getElementById('sort');

productsSelect.addEventListener('change', updateTable);
sortSelect.addEventListener('change', updateTable);

function updateTable() {

    // Clear the existing table rows
    productTableBody.innerHTML = '';

    // Get selected options
    let selectedProduct = productsSelect.value;
    let selectedSort = sortSelect.value;

    if (selectedProduct === 'All' && selectedSort === 'Default') {
        fetchAllProducts();
        return;
    }
    fetchSortedProducts(selectedProduct, selectedSort);
}

function insertProductRow(product, rows) {
    const row = productTableBody.insertRow();
    const nameCell = row.insertCell(0);
    const priceCell = row.insertCell(1);
    const creatorCell = row.insertCell(2);
    const typeCell = row.insertCell(3);
    const copiesCell = row.insertCell(4);

    nameCell.textContent = product.name;
    priceCell.textContent = `$${product.price.toFixed(2)}`;
    creatorCell.textContent = product.creator;
    copiesCell.textContent = product.numCopies;
    typeCell.textContent = product.type;

    // Add the row to the 'rows' array
    rows.push(row);

    row.addEventListener('click', () => {
        // Remove highlight from previously selected rows
        rows.forEach(r => r.classList.remove('selected'));

        // Add highlight to the clicked row
        row.classList.add('selected');
    });
}

function fetchData(url, successCallback, errorCallback) {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            successCallback(data); // Call the success callback with the fetched data
        })
        .catch(error => {
            errorCallback(error); // Call the error callback with the error object
        });
}

function fetchAllProducts() {
    fetchData('/api/v1/products',
        data => {
            const rows = []; // Define the 'rows' array to store row elements
            data.forEach(product => {
                insertProductRow(product, rows);
            });
        },
        error => {
            // Handle error
            console.error('Error fetching data:', error);
        }
    );
}

function fetchSortedProducts(type, sortBy) {
    fetchData(`/api/v1/products/sort?type=${type}&sort=${sortBy}`,
        data => {
            const rows = []; // Define the 'rows' array to store row elements
            data.forEach(product => {
                insertProductRow(product, rows);
            });
        },
        error => {
            // Handle error
            console.error('Error fetching data:', error);
        }
    );
}

updateTable();