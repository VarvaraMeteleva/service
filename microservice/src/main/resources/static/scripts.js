document.addEventListener('DOMContentLoaded', () => {
    const apiUrl = '/api';
    const kafkaUrl = '/kafka/send';

    const customerForm = document.getElementById('customerForm');
    const customerTableBody = document.getElementById('customerTableBody');
    const kafkaForm = document.getElementById('kafkaForm');
    const kafkaResponse = document.getElementById('kafkaResponse');

    if (customerForm) {
        customerForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const formData = new FormData(customerForm);
            const customer = Object.fromEntries(formData.entries());

            if (customer.id) {
                await fetch(`${apiUrl}/change`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(customer)
                });
            } else {
                await fetch(`${apiUrl}/add`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(customer)
                });
            }

            customerForm.reset();
            loadCustomers();
        });
    }

    if (kafkaForm) {
        kafkaForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const formData = new FormData(kafkaForm);
            const customerId = formData.get('id');

            const response = await fetch(`${kafkaUrl}?id=${customerId}`, {
                method: 'POST'
            });
            const message = await response.text();
            kafkaResponse.innerText = message;
        });
    }

    async function loadCustomers() {
        const response = await fetch(`${apiUrl}/all`);
        const customers = await response.json();
        customerTableBody.innerHTML = '';

        customers.forEach(customer => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.surname}</td>
                <td>${customer.gender}</td>
                <td>${customer.age}</td>
                <td>${customer.phone_number}</td>
                <td>
                    <button onclick="editCustomer(${customer.id})">Edit</button>
                    <button onclick="deleteCustomer(${customer.id})">Delete</button>
                </td>
            `;
            customerTableBody.appendChild(row);
        });
    }

    window.editCustomer = async function(id) {
        const response = await fetch(`${apiUrl}?id=${id}`);
        const customer = await response.json();

        document.getElementById('customerId').value = customer.id;
        document.getElementById('name').value = customer.name;
        document.getElementById('surname').value = customer.surname;
        document.getElementById('gender').value = customer.gender;
        document.getElementById('age').value = customer.age;
        document.getElementById('phone_number').value = customer.phone_number;
    };

    window.deleteCustomer = async function(id) {
        await fetch(`${apiUrl}?id=${id}`, {
            method: 'DELETE'
        });
        loadCustomers();
    };

    if (customerForm) {
        loadCustomers();
    }
});
