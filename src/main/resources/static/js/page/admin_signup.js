const clickAdminButton = () => {
    const checkboxs = document.querySelectorAll(".form-authority-container input[type='checkbox']")
    checkboxs.forEach(checkbox => checkbox.checked = true)
}

const validationForm = () => {
    const userid = document.querySelector('.form-userid-input').value
    const name = document.querySelector('.form-username-input').value
    const pwd = document.querySelector('.form-pwd-input').value
    const pwdCheck = document.querySelector('.form-pwd-check-input').value
    if (isValidUserid(userid) && isValidName(name) && isValidPassword(pwd, pwdCheck)) {
        document.querySelector('.signup-form-wrap').submit()
    }
}

const isValidUserid = (value) => {
    const regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{5,12}$/;
    if (!regExp.test(value)) {
        alert("아이디를 영문/숫자조합5자리이상, 12자리이하로 입력해주세요.")
        return false;
    }
    return true;
}
const isValidName = (value) => {
    if (value.trim().length === 0) {
        alert('이름을 입력해주세요.')
        return false
    }
    return true
}
const isValidPassword = (pwd, pwdCheck) => {
    console.log(pwd, pwdCheck
    )
    const regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,12}$/;
    if (!pwd) {
        alert("비밀번호를 입력해주세요.")
        return false
    }
    if (!pwdCheck) {
        alert("비밀번호 확인란을 입력해주세요.")
        return false
    }
    if (pwd !== pwdCheck) {
        alert("비밀번호가 일치하지 않습니다.")
        return false
    }
    if (!regExp.test(pwd)) {
        alert("비밀번호를 영문/숫자조합8자리이상, 12자리이하로 입력해주세요.")
        return false
    }
    return true;
}