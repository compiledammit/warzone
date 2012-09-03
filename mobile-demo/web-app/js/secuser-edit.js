

function SecUser() {
	this.secuser = [];
}

$('#section-show-secuser').live('pageshow', function(e) {
	var url = $(e.target).attr("data-url");
	var matches = url.match(/\?id=(.*)/);
	
	if (matches != null) {
		showSecUser(matches[1]);
	} else {
		createSecUser();
	}
});



function createSecUser() {
	resetForm("form-update-secuser");
    	
    
    navigator.geolocation.getCurrentPosition(foundLocationCreate);
    
    
	$("#delete-secuser").hide();
}




function foundLocationCreate(position) {
	 $("#input-secuser-latitude").val(position.coords.latitude);
	 $("#input-secuser-longitude").val(position.coords.longitude);
	 showMap(position.coords.latitude, position.coords.longitude);
}

function foundLocationUpdate(position) {
	 $("#input-secuser-latitude").val(position.coords.latitude);
	 $("#input-secuser-longitude").val(position.coords.longitude);
}

function showMap(lat,lng) {
	 var latlng = new google.maps.LatLng(lat, lng);
	 var myOptions = {
       zoom: 18,
	   center: latlng,
	   mapTypeId: google.maps.MapTypeId.ROADMAP
	 };
	 var map = new google.maps.Map(document.getElementById("map_canvas"),myOptions);
	 var marker = new google.maps.Marker({
  	   position: new google.maps.LatLng(lat, lng),
	   map: map
	 });
}


function showSecUser(id) {
	resetForm("form-update-secuser");
	var secuser = $("#section-list-secusers").data("SecUser_" + id);
    
	$('#input-secuser-accountExpired').prop('checked', secuser.accountExpired);
	$('#input-secuser-accountExpired').checkboxradio('refresh');
    
	$('#input-secuser-accountLocked').prop('checked', secuser.accountLocked);
	$('#input-secuser-accountLocked').checkboxradio('refresh');
    
	$('#input-secuser-enabled').prop('checked', secuser.enabled);
	$('#input-secuser-enabled').checkboxradio('refresh');
    
	$('#input-secuser-password').val(secuser.password);
    
	$('#input-secuser-passwordExpired').prop('checked', secuser.passwordExpired);
	$('#input-secuser-passwordExpired').checkboxradio('refresh');
    
	$('#input-secuser-username').val(secuser.username);
    
	$('#input-secuser-id').val(secuser.id);
	$('#input-secuser-version').val(secuser.version);
	$('#input-secuser-class').val(secuser.class);
	
    showMap(secuser.latitude, secuser.longitude);
    navigator.geolocation.getCurrentPosition(foundLocationUpdate);
		
	$("#delete-secuser").show();
}

SecUser.prototype.renderToHtml = function() {
};

function resetForm(form) {
	var div = $("#" + form);
	div.find('input:text, input:hidden, input[type="number"], input:file, input:password').val('');
	div.find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected').checkboxradio('refresh');
}

function serializeObject(inputs) {
	var arrayData, objectData;
	arrayData = inputs;
	objectData = {};

	$.each(arrayData, function() {
		var value, classtype;
		var add = true;
		if (this.type == 'select-one') {
			value = $(this).val();
		} else if (this.type == 'radio') {
			if ($(this).is(':checked')) {
				value = this.value;
			} else {
				add = false;
			}
		} else if (this.type == 'checkbox') {
			value = this.checked;
		} else {
			if ($(this).attr('data-role') == 'calbox') {
				value = $(this).data('calbox').theDate;
			} else if (this.value != null) {
				value = this.value;
			} else {
				value = '';
			}
		}
		if (add) {
			if ($(this).attr('data-gorm-relation') == "many-to-one") {
				objectData[this.name+'.id'] = value; 
			} else {
				objectData[this.name] = value;
			}
		}
	});

	return objectData;
}


$("#submit-secuser").live("click tap", function() {
	var div = $("#form-update-secuser");
	var inputs = div.find("input, select");
	var obj = serializeObject(inputs);
	var action = "update";
	if (obj.id == "") {
		action= "save";
	}
	var txt = {
		secuser : JSON.stringify(obj)
	};

	$.ajax({
		cache : false,
		type : "POST",
		async : false,
		data : txt,
		dataType : "jsonp",
		url : serverUrl + '/secuser/' + action,
		success : function(data) {
			if (data.message) {
				alert(data.message)
				return;
			}
			if (data.errors) {
				// Here I need to add to field mapping for errors
				alert("validation issue" + data.errors)
				return;
			}
			if (action == "save") {
				addSecUserOnSection(data);
				$('#list-secusers').listview('refresh');
				
				refreshCenterZoomMap();
				
			} else {
				var secuser = $("#section-list-secusers").data('SecUser_' + data.id);
				$(secuser).trigger("refresh-secuser"+ data.id + "-list", data);
			}
		},
		error : function(xhr) {
			alert(xhr.responseText);
		}
	});

});


$("#delete-secuser").live("click tap", function() {
	var secuserId = $('#input-secuser-id').val();
	var txt = { id : secuserId };
	$.ajax({
		cache : false,
		type : "POST",
		async : false,
		data : txt,
		dataType : "jsonp",
		url : serverUrl + '/secuser/delete',
		success : function(data) {
			if (data.message) {
				alert(data.message)
				return;
			}
			removeSecUserOnSection(data.id);
		},
		error : function(xhr) {
			alert(xhr.responseText);
		}
	});
});