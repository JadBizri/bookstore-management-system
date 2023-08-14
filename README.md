# The Bookstore Management System Web App

This is a simple web application for managing products. It allows users to perform CRUD operations (Create, Read,
Update, Delete) on products. The frontend is built with HTML, CSS, and JavaScript, and the backend is powered by Spring 
Boot and a PostgreSQL database.

## Features

- View a list of products with details like name, price, creator/artist, type, and number of copies.
- Add new products to the database.
- Edit existing products, including handling different types (Book, CD, DVD).
- Delete products from the database.
- Sort products by price and type.

## Setup and Usage

1. **Usage:**

    - View the list of products on the main page.
    - Select a product from the list by clicking on a row from the table.
    - Click on the "Add Product" button to open the form for adding a new product.
    - Click on the "Edit" button in a product's row to modify its details.
    - Click on the "Delete" button in a product's row to remove it from the database.
    - Sort products by price and type using the dropdown menus.
    - Show only a certain type of products.

2. **CRUD Operations:**

    - **Create:** Fill out the "Add Product" form with details and click the "Submit" button.
    - **Read:** Products are displayed in a table on the main page.
    - **Update:** Click the "Edit" button when a product's row is selected, make changes, and click "Save Changes".
    - **Delete:** Click the "Remove" button to remove it from the database.

## Technologies Used

- Frontend: HTML, CSS, JavaScript
- Backend: Java, Spring Boot
- Database: PostgreSQL

## Testing

This project includes comprehensive testing to ensure its functionality and stability. The tests are written using
[JUnit](https://junit.org/junit5/) and [MockMvc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/MockMvc.html)
for testing the Spring MVC controllers.

The tests cover various aspects of the application, including:

- Creating new products
- Retrieving products by different criteria (type, sorting)
- Updating existing products
- Deleting products

Each test case ensures that the application behaves as expected under different scenarios. If you encounter any issues
while running the tests, please refer to the documentation or seek assistance.

## Credits

- Project developed by [Jad Bizri](https://linkedin.com/in/jadbizri)
- Icons from [Font Awesome](https://fontawesome.com/)