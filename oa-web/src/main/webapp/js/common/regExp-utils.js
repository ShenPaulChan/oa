/**
 * 
 */
var regExp = {};

regExp.numberRegExp = /^[0-9]*[1-9][0-9]*$/;
regExp.numberBeginWithZero = /^[0-9]*[0-9][0-9]*$/;
regExp.phone = /(^1(3|4|5|7|8)\d{9}$)|(^0(\(\d{2,3}\)|\d{2,3}-|\s)?\d{7,14}$)|(^\d\d{5,6}\d$)/;
regExp.mobile = /^1(3|4|5|7|8)\d{9}$/;
regExp.money = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;

regExp.regExp = function(text, reg){
	if(text == null){
		return false;
	}
	var result = text.match(regExp.numberRegExp);
	if(result == null){
		return false;
	}
	return true;
}

regExp.isPositiveNumberWithZero = function(text){
    return regExp.regExp(text, regExp.numberBeginWithZero);
}

regExp.isPositiveNumber = function(text){
	return regExp.regExp(text, regExp.numberRegExp);
}