/* global fetch, location */

function deleteProduct(endpoint, id) {
    if (confirm("Bạn có chắc chắn muốn xóa?")) {
        fetch(`${endpoint}/${id}`, {
            method: "DELETE"
        })
        .then(res => {
            if (res.status === 204) {
                alert("Xóa thành công");
                location.reload();
            } else {
                alert("Hệ thống có lỗi");
            }
        }) ; 
    }
}