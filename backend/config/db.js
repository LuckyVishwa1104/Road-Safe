const mongoose = require('mongoose');

require('dotenv').config();
const connection = mongoose.createConnection(process.env.MONGODB_CONN_URL).on('open', () => {
    console.log("MongoDB Connected.");
}).on('error', () => {
    console.log("MongoDB not connected.")
});


module.exports = connection;
