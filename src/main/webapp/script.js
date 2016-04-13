/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





$(document).ready(function () {

// Call to getAllActivities each time the page loads
getAllActivities();   

//  POST with "Add Activity"
    $("#btnSubmit").click(function () {

        $.post("http://localhost:8080/mavenproject2/MyServlet",
                {
                    description: $("#description").val(),
                    duedate: $("#duedate").val(),
                    done: $("#done").val()
                });
                
                hideAddForm();  
                getAllActivities();
                    
    });

/*
    //btn getAllActivities 
    $("#getAllActivities").click(function () {
        getAllActivities();
    });
*/

    //  GET
    function getAllActivities() {

        //$("#getAllActivities").click(function () {

        //  Clear table
        $("#tbody").empty();
        $.get("http://localhost:8080/mavenproject2/MyServlet", function (responseJson) {

            //  Get activity data from servlet
            $.each(responseJson, function (index, activity) {
                $("#tbody").append("<tr><td class='id'>" + activity.id + "</td>" +
                        "<td class='description'>" + activity.description + "</td>" +
                        "<td class='duedate'>" + activity.duedate + "</td>" +
                        "<td class='done'>" + activity.done + "</td>" +
                        "<td>" + "<button class='btnEdit btn btn-default'>EDIT</button>" +
                        //Delete
                        "<td>" + "<form action='http://localhost:8080/mavenproject2/MyServlet' method='POST'>" +
                        "<input type='hidden' value='" + activity.id + "' name='delete'/>" +
                        "<input type='submit' name='btnDelete' value='DELETE' class='btn btn-default'/>" + "</td>" +
                        "</tr>");
            });

            //  Add eventlisteners to edit-buttons
            $(".btnEdit").click(function () {
                var id = $(this).parent().siblings("td.id").text();
                var description = $(this).parent().siblings("td.description").text();
                var duedate = $(this).parent().siblings("td.duedate").text();
                var done = $(this).parent().siblings("td.done").text();


                //  Fill editform with values
                $("#editH2").text("Edit Activity " + id);

                $("#editID").val(id);
                $("#editDescription").val(description);
                $("#editDuedate").val(duedate);
                if (done === ("DONE")) {
                    $("#editDone").prop('checked', true);
                }

                //  Call to show editform
                showEditForm();

            });
        });

    }/*)*/
    ;

    $("#btnAddNewActivity").click(function () {
        $("form").trigger("reset");
        showAddForm();
    });

// Add eventlisteners to cancel buttons
    $(".btnCancelEdit").click(function () {
        hideEditForm();
    });

    $("#btnCancel").click(function () {
        hideAddForm();
    });

// hide Add form
    function hideAddForm() {
        $("#addForm").hide(500);
    }

// show Add form
    function showAddForm() {

        // hide edit form if visible     
        var query = $('#editForm');
        var isVisible = query.is(':visible');

        if (isVisible === true) {
            hideEditForm();
        }
        $("#addForm").show(500);
    }


// hide Edit form
    function hideEditForm() {
        $("#editForm").hide(500);
    }

// show Edit form
    function showEditForm() {

        // hide edit form if visible     
        var query = $('#addForm');
        var isVisible = query.is(':visible');

        if (isVisible === true) {
            hideAddForm();
        }
        $("#editForm").show(500);
    }

});
