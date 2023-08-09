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
        if(row.classList.contains('selected')){
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

showFormButton.addEventListener('click', function() {
    // Show the overlay and modal on button click
    overlay.style.display = 'flex';
});

closeFormButton.addEventListener('click', function() {
    // Close the overlay and modal on button click
    overlay.style.display = 'none';
});

//on load, update table
window.onload = function() {
    updateTable();
};

