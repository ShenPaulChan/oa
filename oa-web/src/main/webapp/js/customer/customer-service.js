/**
 * Created by Paul Chan on 2017/4/21.
 */
var customer_service = {};

customer_service.save_cus = function(params, callback){
    request_util.post(base+'/oa/customer/save', params, callback);
}

customer_service.getCusById = function(customerId, callback){
    request_util.post(base+'/oa/customer/info', {customerId:customerId}, callback);
}

customer_service.getUserGroup = function(callback){
    request_util.post(base+'/oa/customer/groups', {}, callback);
}

customer_service.add_cus_group = function(params, callback){
    request_util.post(base+'/oa/customer/groups/add', params, callback);
}

customer_service.save_cus_group = function(params, callback){
    request_util.post(base+'/oa/customer/cusGroup/update', params, callback);
}

customer_service.del_group = function(cusGroupId, callback){
    request_util.post(base+'/oa/customer/groups/delete', {cusGroupId:cusGroupId}, callback);
}
customer_service.transfer_cus = function(params, callback){
    request_util.post(base+'/oa/customer/transfer', params, callback);
}