$(document).ready(function() {

    $('#viewBuddyButton').click(function(event) {
        event.preventDefault();

        let addressBookId = $('#addressBookId').val();

        // AJAX call to get the buddy list for the specified address book ID
        $.ajax({
            type: 'GET',
            url: '/addressbook/' + addressBookId,
            success: function(response) {
                // Just display the buddies list
                // since no buddies are being added or removed
                updateBuddiesList(addressBookId);
            },
            error: function(error) {
                console.error('Error fetching buddies:', error);
            }
        });
    });

    $('#addBuddyButton').click(function(event) {
        event.preventDefault();

        let addressBookId = $('#addressBookId').val();
        let buddy = {
            name: $('#buddyName').val(),
            phoneNumber: $('#buddyPhoneNumber').val(),
            address: $('#buddyAddress').val()
        };

        $.ajax({
            type: 'GET',
            url: '/addressbook/' + addressBookId,
            success: function(responseData) {
                if (responseData === "") {
                    // If responseData is an empty string, then the AddressBook has not been
                    // created yet
                    createAddressBook(addressBookId, function() {
                        addBuddy(addressBookId, buddy);
                    });
                } else {
                    // Address book exists. Add the buddy
                    addBuddy(addressBookId, buddy);
                }
            }
        });
    });

    $('#deleteBuddyButton').click(function(event) {
        event.preventDefault();

        let addressBookId = $('#addressBookId').val();
        let buddyId = $('#buddyId').val();

        $.ajax({
            type: 'DELETE',
            url: '/addressbook/' + addressBookId + '/removeBuddy/' + buddyId,
            success: function(responseData) {
                console.log("Buddy deleted successfully: ", JSON.stringify(responseData));
                updateBuddiesList(addressBookId);
            }
        });
    });

    function createAddressBook(addressBookId, callback) {
        $.ajax({
            type: 'POST',
            url: '/addressbook/',
            data: JSON.stringify({ id: addressBookId }),
            contentType: 'application/json',
            success: callback,
            error: function(error) {
                console.error('Error creating address book:', error);
            }
        });
    }

    function addBuddy(addressBookId, buddy) {
        $.ajax({
            type: 'PUT',
            url: '/addressbook/' + addressBookId + '/addBuddy',
            data: JSON.stringify(buddy),
            contentType: 'application/json',
            success: function(response) {
                updateBuddiesList(addressBookId);
            },
            error: function(error) {
                console.error('Error adding buddy:', error);
            }
        });
    }

    function updateBuddiesList(addressBookId) {
        // Fetch the updated buddies list
        $.ajax({
            type: 'GET',
            url: '/addressbook/' + addressBookId,
            success: function(response) {
                let buddies = response.buddyInfo;
                $('#buddiesList').empty();
                buddies.forEach(function(buddy) {
                    $('#buddiesList').append('<li>' + 'Buddy ID: ' + buddy.id + ' - ' + buddy.name + '(' + buddy.phoneNumber + ', ' + buddy.address + ')</li>');
                });
            },
            error: function(error) {
                console.error('Error fetching buddies:', error);
            }
        });
    }

});
