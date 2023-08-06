fetch('/api/v1/products')
            .then(response => response.json())
            .then(data => {
                const productList = document.getElementById('productList');
                data.forEach(product => {
                    const productElement = document.createElement('div');
                    productElement.textContent = product.name;
                    productList.appendChild(productElement);
                });
            })
            .catch(error => console.error('Error fetching data:', error));