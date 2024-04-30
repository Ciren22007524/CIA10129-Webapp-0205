// 等待DOM內容加載完成後執行
document.addEventListener("DOMContentLoaded", function() {
    // 1. 在側邊欄中尋找與當前頁面路徑匹配的元素，並將其設置為活動狀態。
    var currentPagePath = window.location.pathname; // 獲取當前頁面的路徑
    var sidebarLinks = document.querySelectorAll('#accordionSidebar a.nav-link'); // 選擇所有側邊欄中的鏈接
    sidebarLinks.forEach(function(link) {
        if (link.getAttribute('href') === currentPagePath) { // 如果鏈接的href與當前頁面路徑匹配
            link.classList.add('active'); // 添加活動狀態類
            var parentCollapse = link.closest('.collapse'); // 獲取最近的包含該鏈接的折疊元素
            if (parentCollapse) {
                parentCollapse.classList.add('show'); // 如果存在折疊元素，添加展開類
            }
        }
    });

    // 2. 獲取頁面標題或其他有關頁面的信息。
    var pageTitle = document.title; // 獲取當前頁面的標題

    // 3. 使用 JavaScript 將這些信息動態地填充到麵包屑中。
    var breadcrumb = document.querySelector('.breadcrumb'); // 選擇麵包屑元素
    if (breadcrumb) {
        var breadcrumbItem = document.createElement('li'); // 創建新的麵包屑項目
        breadcrumbItem.classList.add('breadcrumb-item'); // 添加麵包屑項目的類
        breadcrumbItem.textContent = pageTitle; // 將頁面標題設置為麵包屑項目的內容
        breadcrumb.appendChild(breadcrumbItem); // 添加麵包屑項目到麵包屑元素中
    }
});