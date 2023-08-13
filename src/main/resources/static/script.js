//on load, update table
window.onload = function () {
    updateTable();
};

//where rows will be inserted
const productTableBody = document.getElementById('productTableBody');

const productsSelect = document.getElementById('products'); //show product drop down
const sortSelect = document.getElementById('sort'); //sort drop down

//update table on any change of any drop down
productsSelect.addEventListener('change', updateTable);
sortSelect.addEventListener('change', updateTable);

//GET functionality

//function to update the table based on selected options
function updateTable() {

    //clear the existing table rows
    productTableBody.innerHTML = '';

    //get selected options
    let selectedProduct = productsSelect.value;
    let selectedSort = sortSelect.value;

    fetchSortedProducts(selectedProduct, selectedSort);
}

//function to insert a new row into the product table with product data
function insertProductRow(product, rows) {
    //new row
    const row = productTableBody.insertRow();

    //cells for each column
    const idCell = row.insertCell(0);
    const nameCell = row.insertCell(1);
    const priceCell = row.insertCell(2);
    const creatorCell = row.insertCell(3);
    const typeCell = row.insertCell(4);
    const copiesCell = row.insertCell(5);

    //populate cells
    idCell.textContent = product.id;
    nameCell.textContent = `${product.name} (${product.year})`;
    priceCell.textContent = `$${product.price.toFixed(2)}`;
    creatorCell.textContent = product.creator;
    copiesCell.textContent = product.numCopies;
    typeCell.textContent = product.type;

    //add the row to the 'rows' array for later reference
    rows.push(row);

    //add a click event listener to highlight the clicked row
    row.addEventListener('click', () => {
        highlightRow(row, rows);
    });
}

const icon = document.querySelector('.fa-pen-to-square'); //to change icon color
let selectedProduct = null; //to store selected product for later use
//function that highlights a selected row
function highlightRow(row, rows) {
    //if row is already selected, then unselect and return
    if (row.classList.contains('selected')) {
        row.classList.remove('selected');
        selectedProduct = null;
        showEditFormButton.classList.add('disabled');
        icon.style.color = 'grey';
        return;
    }

    //make edit button clickable
    showEditFormButton.classList.remove('disabled');
    icon.style.color = 'yellow';

    //remove highlight from previously selected rows
    rows.forEach(r => r.classList.remove('selected'));

    //add highlight to the clicked row
    row.classList.add('selected');

    //extract name only without year from table
    const name = row.cells[1].textContent.replace(/\s*\(.*?\)\s*/g, '');
    //extract the year by using regular expression to match a four-digit number
    const yearMatch = row.cells[1].textContent.match(/\d{4}/);
    const year = yearMatch ? parseInt(yearMatch[0]) : null;

    //get the product information from the selected row
    selectedProduct = {
        id: BigInt(parseInt(row.cells[0].textContent)),
        name: name,
        price: parseFloat(row.cells[2].textContent.replace('$', '')),
        creator: row.cells[3].textContent,
        type: row.cells[4].textContent,
        numCopies: parseInt(row.cells[5].textContent),
        year: year
    };
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

//POST functionality

//add new product form
const showFormButton = document.getElementById('showFormButton'); //the add button
const closeFormButton = document.getElementById('closeFormButton'); //cancel button on form
const overlay = document.getElementById('overlay'); //the form overlay

//show form on click
showFormButton.addEventListener('click', function () {
    //show the overlay and modal on button click
    overlay.style.display = 'flex';
});

//hide form on click
closeFormButton.addEventListener('click', function () {
    //close the overlay and modal on button click
    overlay.style.display = 'none';
});

//select the form element and listen for the submit event
const addForm = document.getElementById('addProductForm'); //the add form

//attach the function as the event listener
addForm.addEventListener('submit', handleAddFormSubmit);

//function that handles the add product form submission
function handleAddFormSubmit(event) {
    event.preventDefault(); //prevent the default form submission

    //collect form data
    const formData = new FormData(addForm);

    //convert form data to JSON, key (i.e. 'name') and value (i.e. 'Harry Potter Book')
    const productData = {};
    formData.forEach((value, key) => {
        productData[key] = value;
    });

    //if it is a book then ask for ISBN in addition
    if (productData.type === 'Book') {
        let isbn = prompt("What is the book's ISBN?");
        if (isbn == null || isbn === "") {
            isbn = "Unknown";
        }
        productData['isbn'] = isbn;

        //add new book
        sendNewProduct('/api/v1/products/book', productData).then(() => {
            updateTable();
            overlay.style.display = 'none';
        });
    } else if (productData.type === 'CD') {
        //add new CD
        sendNewProduct('/api/v1/products/cd', productData).then(() => {
            updateTable();
            overlay.style.display = 'none';
        });

    } else if (productData.type === 'DVD') {
        //add new DVD
        sendNewProduct('/api/v1/products/dvd', productData).then(() => {
            updateTable();
            overlay.style.display = 'none';
        });
    }
}

//POST request function that sends data to add a new product to the database
async function sendNewProduct(url, productData) {
    try {
        //send a POST request to the server
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(productData),
        });

        //handle error
        if (!(response.ok)) {
            await response.json();
            //display an error message to the user
            alert("Error. Make sure you don't add something that already exists!");
        }
    } catch (error) {
        console.error('Error:', error);
        //display an error message to the user
        alert('An error occurred while sending the data. Please try again.');
    }
}

//PUT functionality

//edit product form
const showEditFormButton = document.getElementById('edit-button'); //the edit button
const closeEditFormButton = document.getElementById('closeFormButton2'); //cancel button on form
const editOverlay = document.getElementById('overlay2'); //the form overlay

//input elements in edit form
const nameInput = document.getElementById('productName2');
const priceInput = document.getElementById('productPrice2');
const creatorInput = document.getElementById('creator2');
const typeInput = document.getElementById('productType2');
const copiesInput = document.getElementById('numCopies2');
const yearInput = document.getElementById('productYear2');

//show edit form on click
showEditFormButton.addEventListener('click', function () {
    if (selectedProduct) {
        //pre-fill the form with the selected product's information
        nameInput.value = selectedProduct.name;
        creatorInput.value = selectedProduct.creator;
        typeInput.value = selectedProduct.type;
        copiesInput.value = selectedProduct.numCopies;
        yearInput.value = selectedProduct.year;
        priceInput.value = selectedProduct.price;

        //display the edit form
        editOverlay.style.display = 'flex';
    }
});

//hide form on click
closeEditFormButton.addEventListener('click', function () {
    //close the overlay and modal on button click
    editOverlay.style.display = 'none';
});

//select the edit form element and listen for the submit event
const editForm = document.getElementById('editProductForm'); //the edit form

//attach the function as the event listener
editForm.addEventListener('submit', handleEditFormSubmit);

//function that handles the add product form submission
function handleEditFormSubmit(event) {
    event.preventDefault(); //prevent the default form submission

    //collect form data
    const formData = new FormData(editForm);

    //convert form data to JSON, key (i.e. 'name') and value (i.e. 'Harry Potter Book')
    const productData = {};
    formData.forEach((value, key) => {
        productData[key] = value;
    });

    if (productData.type === 'Book') {
        fetch(`/api/v1/products/${selectedProduct.id}`, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(product => {
                //if it is a book then ask for ISBN in addition
                let isbn = prompt("Update Book's ISBN?", product.isbn);
                if (isbn === "") {
                    isbn = null;
                }
                productData['isbn'] = isbn;

                //update the book
                sendUpdatedProduct(productData, selectedProduct.id).then(() => {
                    updateTable();
                    editOverlay.style.display = 'none';
                });
            })
            .catch(error => {
                console.error('Error fetching product:', error);
            });
    }

    //update product
    else {
        sendUpdatedProduct(productData, selectedProduct.id).then(() => {
            updateTable();
            editOverlay.style.display = 'none';
        });
    }
}

//function that sends the data to be updated in the selected product
async function sendUpdatedProduct(productData, id) {
    try {
        //send a PUT request to the server
        const response = await fetch(`/api/v1/products/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(productData),
        });

        //handle error
        if (!(response.ok)) {
            await response.json();
            //display an error message to the user
            alert("Error. Make sure you don't edit it to something that already exists!");
        }
    } catch (error) {
        console.error('Error:', error);
        //display an error message to the user
        alert("Error. Make sure you don't edit it to something that already exists!");
    }
}