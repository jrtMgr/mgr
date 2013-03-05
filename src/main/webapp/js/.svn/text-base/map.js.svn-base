//construction
function Map() {
	this.obj = new Object();
};

// add a key-value
Map.prototype.put = function(key, value) {
	this.obj[key] = value;
};

// get a value by a key,if don't exist,return undefined
Map.prototype.get = function(key) {
	return this.obj[key];
};

// remove a value by a key
Map.prototype.remove = function(key) {
	if (this.get(key) == undefined) {
		return;
	}
	delete this.obj[key];
};

// clear the map
Map.prototype.clear = function() {
	this.obj = new Object();
};

// get the size
Map.prototype.size = function() {
	var ary = this.keys();
	return ary.length;
};

// get all keys
Map.prototype.keys = function() {
	var ary = new Array();
	for ( var temp in this.obj) {
		ary.push(temp);
	}
	return ary;
};

// get all values
Map.prototype.values = function() {
	var ary = new Array();
	for ( var temp in this.obj) {
		ary.push(this.obj[temp]);
	}
	return ary;
};
