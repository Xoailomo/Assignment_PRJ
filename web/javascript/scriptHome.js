/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// script.js
// Update "If approved" days dynamically
document.querySelector('#duration').addEventListener('change', function() {
    const duration = this.value;
    const totalAllowance = parseInt(document.querySelector('#total-allowance').textContent); // Get from rendered JSP
    const ifApprovedElement = document.querySelector('#if-approved');

    if (duration === 'one-day') {
        ifApprovedElement.textContent = `${totalAllowance - 1} Days`;
    } else if (duration === 'multiple-days') {
        ifApprovedElement.textContent = 'TBD'; // Placeholder
    }
});

// Filter functionality for the agenda
document.querySelector('#name-filter').addEventListener('input', function() {
    const filterValue = this.value.toLowerCase();
    const rows = document.querySelectorAll('.agenda tbody tr');

    rows.forEach(row => {
        const name = row.cells[0].textContent.toLowerCase();
        if (name.includes(filterValue)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
});

