
const minLengthValidator = (value, minLength) => {
    return value.length >= minLength;
};

const requiredValidator = value => {
    return value.trim() !== '';
};

const emailValidator = value => {
    return (String(value) === "ADMIN"||String(value) === "CLIENT");
};

const validate = (value, rules) => {
    let isValid = true;

    for (let rule in rules) {

        switch (rule) {
            case 'minLength': isValid = isValid && minLengthValidator(value, rules[rule]);
                              break;

            case 'isRequired': isValid = isValid && requiredValidator(value);
                               break;

            case 'emailValidator': isValid = isValid && emailValidator(value);
                                   break;

            default: isValid = true;
        }

    }

    return isValid;
};

export default validate;
