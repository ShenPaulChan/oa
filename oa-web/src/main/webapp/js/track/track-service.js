/**
 * Created by Paul Chan on 2017/4/23.
 */
var track_service = {};

track_service.add_track = function(params, callback){
    request_util.post(base+'/oa/track/add', params, callback);
}
