
function SecUserList() {
	this.secusers = [];
}

SecUserList.prototype.add = function(listSecUser) {
	this.secusers = listSecUser;
};

SecUserList.prototype.get = function(index) {
	return this.secusers[index];
};

serverUrl = 'http://localhost:8080/mobile-demo';
//serverUrl = 'http://mobile-demo.cloudfoundry.com'	

var global_map;
var pointMap = {}; 


$('#section-list-secusers').live('pageinit', function(e) {
	
	// Initialization of Map for list display
	var center = new google.maps.LatLng(0, 0);
	var myOptions = {
       zoom: 18,
	   center: center,
	   mapTypeId: google.maps.MapTypeId.ROADMAP
	};
    global_map = new google.maps.Map(document.getElementById("map-canvas-all"), myOptions);
	
    // Get domain objects		
	getSecUsers();
});


$('#list-all-secusers').live('click tap', function(e, ui) {
	hideMapDisplay();
	showListDisplay();
});

$('#map-all-secusers').live('click tap', function(e, ui) {
	hideListDisplay();
	showMapDisplay();
});

function hideListDisplay() {
	$('#list-secusers-parent').removeClass('visible');
	$('#list-secusers-parent').addClass('invisible');
}

function showMapDisplay() {
	$('#map-secusers-parent').removeClass('invisible');
	$('#map-secusers-parent').addClass('visible');
}

function showListDisplay() {
	$('#list-secusers-parent').removeClass('invisible');
	$('#list-secusers-parent').addClass('visible');
}

function hideMapDisplay() {
	$('#map-secusers-parent').removeClass('visible');
	$('#map-secusers-parent').addClass('invisible');
}


function getSecUsers() {
	$.ajax({
		cache : false,
		type : "GET",
		async : false,
		dataType : "json",
		url : serverUrl + '/secUser/list',
		success : function(data) {
			if (data) {
				var secuserList = new SecUserList();
				secuserList.add(data);
				secuserList.renderToHtml();
			}
		},
		error : function(xhr) {
            console.log(xhr);
			alert(xhr.responseText);
		}
	});
}

SecUserList.prototype.renderToHtml = function() {
	var context = this.secusers;
	for ( var i = 0; i < context.length; i++) {
		var secuser = context[i];
		addSecUserOnSection(secuser);
	}
	$('#list-secusers').listview('refresh');
	
	refreshCenterZoomMap();
	
}


function addSecUserOnSection (secuser) {
	var out = '<li><a href="#section-show-secuser?id='+ secuser.id + '" data-transition="fade" id="secuser'; 
	out =  out + secuser.id + '-in-list">';
	
    out = out + secuser.accountExpired +';';
       out = out + secuser.accountLocked +';';
       out = out + secuser.enabled +';';
       out = out + secuser.password +';';
       out = out + secuser.passwordExpired +';';
       out = out + secuser.username +';';
       
	out = out + '</a></li>';
	
	$("#section-list-secusers").data('SecUser_' + secuser.id, secuser);
	
	var marker = addMarkers(secuser);
	
	
	$(secuser).bind("refresh-secuser" + secuser.id + "-list", function(bind, newSecUser) {
		var secuser = $("#section-list-secusers").data('SecUser_' + newSecUser.id);
		var textDisplay = "";
	    textDisplay = textDisplay + newSecUser.accountExpired +';';
	       textDisplay = textDisplay + newSecUser.accountLocked +';';
	       textDisplay = textDisplay + newSecUser.enabled +';';
	       textDisplay = textDisplay + newSecUser.password +';';
	       textDisplay = textDisplay + newSecUser.passwordExpired +';';
	       textDisplay = textDisplay + newSecUser.username +';';
	       
		$('#secuser' + newSecUser.id + '-in-list').text(textDisplay);
		for(var property in newSecUser) {
			secuser[property] = newSecUser[property];
		}
		
		var marker = pointMap['SecUser_' + secuser.id + "_marker"];
		marker.setMap(null);
		var newMarker = addMarkers(newSecUser);
		refreshCenterZoomMap();
		
	});
	$("#list-secusers").append(out);
}

function addMarkers(secuser) {
	var marker = new google.maps.Marker({
		   position: new google.maps.LatLng(secuser.latitude, secuser.longitude),
		   map: global_map
		 });
	pointMap['SecUser_' + secuser.id + "_marker"] = marker;
}

function refreshCenterZoomMap() {
	var bounds = new google.maps.LatLngBounds();
	
	var previousZoom = global_map.getZoom();

	for (var key in pointMap) {
		var marker = pointMap[key];
		bounds.extend(marker.getPosition());
	}
	global_map.setCenter(bounds.getCenter());
	global_map.fitBounds(bounds);

	zoomChangeBoundsListener = 
	  google.maps.event.addListenerOnce(global_map, 'bounds_changed', function(event) {
		  if (!this.getZoom()){
			  this.setZoom(previousZoom);
		  }	
	  });
	setTimeout(function(){google.maps.event.removeListener(zoomChangeBoundsListener)}, 2000);	
	
}


function removeSecUserOnSection(id) {
	var listID = 'secuser' + id + '-in-list';
	var link = $("#" + listID);
	link.parents('li').remove();
	var secuser = $("#section-list-secusers").data('SecUser_' + id, secuser);
	$("#section-list-secusers").removeData('SecUser_' + id);
	$(secuser).unbind();
	$('#list-secusers').listview('refresh');
	
	var marker = pointMap['SecUser_' + secuser.id + "_marker"];
	marker.setMap(null);
	delete pointMap['SecUser_' + secuser.id + "_marker"];
	refreshCenterZoomMap();
	
}

