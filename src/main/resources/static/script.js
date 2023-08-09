const productTableBody = document.getElementById('productTableBody');

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

const productsSelect = document.getElementById('products');
const sortSelect = document.getElementById('sort');

productsSelect.addEventListener('change', updateTable);
sortSelect.addEventListener('change', updateTable);

function updateTable() {

    // Clear the existing table rows
    productTableBody.innerHTML = '';

    // Get selected options
    const selectedProduct = productsSelect.value;
    const selectedSort = sortSelect.value;
    switch (selectedProduct) {
        case 'All':
            fetchAllProducts();
            break;
        case 'Books':
            fetchProductsByType('Book');
            break;
        case 'CDs':
            fetchProductsByType('CD');
            break;
        case 'DVDs':
            fetchProductsByType('DVD');
            break;
        default:
            console.error('Something unexpected happened.');
    }
}

updateTable();

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

function fetchProductsByType(type) {
    switch (type) {
        case 'Book':
            fetchData('/api/v1/products/api/v1/products/type?type=Book',
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
            break;
        case 'CD':
            fetchData('/api/v1/products/api/v1/products/type?type=CD',
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
            break;
        case 'DVD':
            fetchData('/api/v1/products/api/v1/products/type?type=DVD',
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
            break;
    }

}