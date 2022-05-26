function movePage(page = 0) {
    page += 1;
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has("currentPage")) urlParams.set("currentPage", page.toString());
    else urlParams.append("currentPage", page.toString());

    location.href = location.pathname + '?' + urlParams.toString();
}