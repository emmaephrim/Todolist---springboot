function showDeleteModal(todoId) {
  const deleteModal = document.getElementById("delete-modal");
  deleteModal.classList.remove("hidden");

  const confirmDeleteBtn = document.getElementById("confirm-delete");
  confirmDeleteBtn.onclick = function () {
    // Submit the form to delete the todo
    document.getElementById(`delete-form-${todoId}`).submit();
  };

  const cancelDeleteBtn = document.getElementById("cancel-delete");
  cancelDeleteBtn.onclick = function () {
    deleteModal.classList.add("hidden");
  };

  const cancelDeleteIcon = document.getElementById("cancel-delete-icon");
  cancelDeleteIcon.onclick = function () {
    deleteModal.classList.add("hidden");
  };
}

document.getElementById("select-all").onclick = function () {
  var checkboxes = document.querySelectorAll('input[name="selectedIds"]');
  for (var checkbox of checkboxes) {
    checkbox.checked = this.checked;
  }
};

function submitSelectedIds() {
  var selectedIds = [];
  var checkboxes = document.querySelectorAll(
    'input[name="selectedIds"]:checked',
  );
  for (var checkbox of checkboxes) {
    selectedIds.push(checkbox.value);
  }
  var selectedOption = document.getElementById("mark-as").value;
  console.log(selectedIds);

  if (selectedOption == "restore" && selectedIds.length > 0) {
    $.ajax({
      url: "/bulk-update-todos",
      method: "POST",
      data: {
        selectedIds: selectedIds,
        newStatus: selectedOption,
      },
      success: function (response) {
        //Handle the response from the backend
        window.location.href = "/"; // Redirect to the home page
      },
      error: function (error) {
        // Handle the error from the backend
      },
    });
    return;
  }

  const deleteModal = document.getElementById("delete-modal");
  deleteModal.classList.remove("hidden");
  const confirmDeleteBtn = document.getElementById("confirm-delete");
  confirmDeleteBtn.onclick = function () {
    // Submit the form to delete the todo
    // document.getElementById(`delete-form-${todoId}`).submit();
    // Submit the selectedIds and selectedOption to the backend
    // using an AJAX request or a form submission
    // Example AJAX request:
    if (selectedIds.length > 0 && selectedOption != "restore") {
      $.ajax({
        url: "/bulk-update-todos",
        method: "POST",
        data: {
          selectedIds: selectedIds,
          newStatus: selectedOption,
        },
        success: function (response) {
          //Handle the response from the backend
          window.location.href = "/"; // Redirect to the home page
        },
        error: function (error) {
          // Handle the error from the backend
        },
      });
    }
  };

  const cancelDeleteBtn = document.getElementById("cancel-delete");
  cancelDeleteBtn.onclick = function () {
    deleteModal.classList.add("hidden");
  };

  const cancelDeleteIcon = document.getElementById("cancel-delete-icon");
  cancelDeleteIcon.onclick = function () {
    deleteModal.classList.add("hidden");
  };
}

// Function to simulate a button click
function hidToast() {
  const toastContainer = document.getElementById("toast");
  if (toastContainer) {
    toastContainer.style.display = "none";
  }
}
// Set a timeout to click the toastContainer 3 seconds after the page is mounted
window.addEventListener("load", () => {
  setTimeout(hidToast, 3000);
});
