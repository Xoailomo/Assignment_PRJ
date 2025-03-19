/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// Toggle sidebar collapse
function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    sidebar.classList.toggle('collapsed');
}

// Highlight active menu item
document.addEventListener('DOMContentLoaded', () => {
    const menuItems = document.querySelectorAll('.menu-item a');
    const currentPath = window.location.pathname;

    menuItems.forEach(item => {
        if (item.getAttribute('href') === currentPath) {
            item.parentElement.classList.add('active');
        }
    });
});



