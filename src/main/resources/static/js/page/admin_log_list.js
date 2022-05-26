(() => {
    const searchHandler = () => {
        const kwd = searchBar.querySelector(".admin-log-search-input").value;

        location.href = `/user/logs?kwd=${kwd}`
    }
    const searchBar = document.querySelector(".admin-log-search");
    const searchSubmit = searchBar.querySelector(".admin-log-search-submit");
    searchSubmit.addEventListener("click", searchHandler);
})()
