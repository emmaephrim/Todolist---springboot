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
  // Submit the selectedIds and selectedOption to the backend
  // using an AJAX request or a form submission
  // Example AJAX request:
  if (selectedIds.length > 0) {
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
        console.log(error);
      },
    });
  }
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
