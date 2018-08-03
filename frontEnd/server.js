// this for the testing preflight error disable 
import proxy from 'http-proxy-middleware';
...
let options = {
 target: baseApiUrl, //api.example.com 
 changeOrigin: true,
 logLevel: ‘debug’,
 onError: function onError(err, req, res) {
 console.log(‘Something went wrong with the proxy middleware.’, err)
 res.end();
 }
};
app.use(‘/api’, proxy(options)); //only forward calls with ‘/api’ route

//  the actual program start from here
var express = require("express");
var app = express();
app.use(express.static(__dirname + '/dist'))
 app.get('/', function (req, res) {
     res.status(200).send('OK')
 })
app.listen(4200,function(request,response){
    
});
