/**
 * Created by Paul Chan on 2017/4/21.
 */
var city_service = {};

city_service.listProvinceCities = function(params, callback){
    request_util.post(base + '/cities', params, callback);
}