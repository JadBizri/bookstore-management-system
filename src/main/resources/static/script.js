//where rows will be inserted
const productTableBody = document.getElementById('productTableBody');

const productsSelect = document.getElementById('products'); //show drop down
const sortSelect = document.getElementById('sort'); //sort drop down

//update table on any change of any drop down
productsSelect.addEventListener('change', updateTable);
sortSelect.addEventListener('change', updateTable);

//function to update the table based on selected options
function updateTable() {

    // Clear the existing table rows
    productTableBody.innerHTML = '';

    // Get selected options
    let selectedProduct = productsSelect.value;
    let selectedSort = sortSelect.value;

    fetchSortedProducts(selectedProduct, selectedSort);
}

//function to insert a new row into the product table with product data
function insertProductRow(product, rows) {
    //new row
    const row = productTableBody.insertRow();

    //cells for each column
    const nameCell = row.insertCell(0);
    const priceCell = row.insertCell(1);
    const creatorCell = row.insertCell(2);
    const typeCell = row.insertCell(3);
    const copiesCell = row.insertCell(4);

    //populate cells
    nameCell.textContent = `${product.name} (${product.year})`;
    priceCell.textContent = `$${product.price.toFixed(2)}`;
    creatorCell.textContent = product.creator;
    copiesCell.textContent = product.numCopies;
    typeCell.textContent = product.type;

    //add the row to the 'rows' array for later reference
    rows.push(row);

    //add a click event listener to highlight the clicked row
    row.addEventListener('click', () => {

        //if row is already selected, then unselect and return
        if (row.classList.contains('selected')) {
            row.classList.remove('selected');
            return;
        }

        //remove highlight from previously selected rows
        rows.forEach(r => r.classList.remove('selected'));

        //add highlight to the clicked row
        row.classList.add('selected');
    });
}

//function to fetch data from the provided URL and handle success or error
function fetchData(url, successCallback, errorCallback) {
    //fetch data from the given URL using the Fetch API
    fetch(url)
        .then(response => response.json()) //parse response as JSON
        .then(data => {
            successCallback(data); //call the success callback with the fetched data
        })
        .catch(error => {
            errorCallback(error); //call the error callback with the error object
        });
}

//function to fetch sorted products optionally based on type and sorting criteria
function fetchSortedProducts(type, sortBy) {

    //construct the URL with the provided type and sortBy parameters
    const url = `/api/v1/products/sort?type=${type}&sort=${sortBy}`;

    //use the fetchData function to fetch data from the constructed URL
    fetchData(url,
        data => {
            const rows = []; //define the 'rows' array to store row elements

            //for each product, insert it in a row
            data.forEach(product => {
                insertProductRow(product, rows);
            });
        },
        error => {
            //handle error by logging an error message
            console.error('Error fetching data:', error);
        }
    );
}

//add product form

const showFormButton = document.getElementById('showFormButton');
const closeFormButton = document.getElementById('closeFormButton');
const overlay = document.getElementById('overlay');

showFormButton.addEventListener('click', function () {
    // Show the overlay and modal on button click
    overlay.style.display = 'flex';
});

closeFormButton.addEventListener('click', function () {
    // Close the overlay and modal on button click
    overlay.style.display = 'none';
});

//on load, update table
window.onload = function () {
    updateTable();
};

// Select the form element and listen for the submit event
const productForm = document.getElementById('addProductForm');
productForm.addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent the default form submission

    // Collect form data
    const formData = new FormData(productForm);

    // Convert form data to JSON
    const productData = {};
    formData.forEach((value, key) => {
        productData[key] = value;
    });

    if (productData.type === 'Book') {
        let isbn = prompt("What is the book's ISBN?");
        if (isbn == null || isbn === "") {
            isbn = "Unknown";
        }
        productData['isbn'] = isbn;
        sendNewProduct('/api/v1/products/book', productData);
    } else if (productData.type === 'CD') {
        sendNewProduct('/api/v1/products/cd', productData);
    } else if (productData.type === 'DVD') {
        sendNewProduct('/api/v1/products/dvd', productData);
    }
    overlay.style.display = 'none';
    updateTable();
});

function sendNewProduct(url, productData) {
    // Send a POST request to the server
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(productData),
    })
        .then(response => response.json())
        .then(data => {
            // Handle success or display a success message
        })
        .catch(error => {
            // Handle error or display an error message
        });
}
