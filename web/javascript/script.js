/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// script.js
// Update "If approved" days dynamically

function toggleDateFields() {
    const duration = document.getElementById('duration').value;
    const singleDay = document.getElementById('single-day');
    const rangeDays = document.getElementById('range-days');
    if (duration === 'Range') {
        singleDay.style.display = 'none';
        rangeDays.style.display = 'block';
        initDateRangePicker();
    } else {
        singleDay.style.display = 'block';
        rangeDays.style.display = 'none';
    }
}

// Set current date cho single day
document.addEventListener('DOMContentLoaded', function () {
    const currentDate = new Date().toISOString().split('T')[0];
    document.getElementById('day').value = currentDate;
});

// Khởi tạo date range picker
function initDateRangePicker() {
    const rangeDate = document.getElementById('range-date');
    const durationText = document.getElementById('duration-text');
    let startDate = new Date();
    let endDate = new Date();
    // Format ngày theo MM/DD/YYYY
    function formatDate(date) {
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const year = date.getFullYear();
        return `${month}/${day}/${year}`;
    }

    // Cập nhật giá trị hiển thị
    function updateDisplay() {
        rangeDate.value = `${formatDate(startDate)} - ${formatDate(endDate)}`;
        const diffDays = Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24)) + 1;
        durationText.textContent = diffDays === 1 ? 'one day requested' : `${diffDays} days requested`;
    }

    // Khởi tạo giá trị mặc định
    startDate.setDate(startDate.getDate() + 1); // Ngày mai làm ngày mặc định
    endDate.setDate(startDate.getDate()); // Cùng ngày với start
    updateDisplay();
    // Thêm event listener cho input (sử dụng flatpickr hoặc thư viện khác để chọn range)
    rangeDate.addEventListener('click', function () {
        // Ở đây bạn có thể tích hợp flatpickr hoặc thư viện date picker khác
        // Ví dụ giả lập: Cho phép người dùng nhập hoặc chọn range
        let newStart = prompt('Enter start date (MM/DD/YYYY):');
        let newEnd = prompt('Enter end date (MM/DD/YYYY):');
        if (newStart && newEnd) {
            startDate = new Date(newStart);
            endDate = new Date(newEnd);
            if (startDate <= endDate) {
                updateDisplay();
            } else {
                alert('End date must be after start date');
            }
        }
    });
}
