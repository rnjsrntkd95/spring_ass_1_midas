(() => {
    const searchHandler = () => {
        const condition = searchBar.querySelector(".admin-search-menu").value;
        const kwd = searchBar.querySelector(".admin-search-input").value;

        location.href = `/user/all?condition=${condition}&kwd=${kwd}`
    }
    const searchBar = document.querySelector(".admin-search");
    const searchSubmit = searchBar.querySelector(".admin-search-submit");
    searchSubmit.addEventListener("click", searchHandler);
})()
