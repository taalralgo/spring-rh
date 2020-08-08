$(document).ready(function () {
    $('.btnEdit').click(function () {
        var id = $(this).attr('data')
        console.log(id)
        $.ajax({
            url: '/employe/edit/' + id,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                $('#id').val(data.id)
                $('#matricule').val(data.matricule)
                $('#nomComplet').val(data.nomComplet)
                $('#poste').val(data.poste)
                $('#salaire').val(data.salaire)
                $('#service').val(data.service.id)
            },
            error: function (error) {
                console.error(error);
            }
        })
    });


    $('.btnRemove').click(function () {
        //
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        })

        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true
        }).then((result) => {
            if (result.value) {
            var id = $(this).attr('data')
            $.ajax({
                url: '/employe/remove/' + id,
                type: 'get',
                success: function (data) {
                    if(data.status === "success") {
                        // swalWithBootstrapButtons.fire(
                        //     'Deleted!',
                        //     'La ligne a été supprimée avec succès.',
                        //     'success'
                        // )
                        window.location.reload()
                    }
                    else {
                        swalWithBootstrapButtons.fire(
                            'Deleted!',
                            'Erreur de suppression.',
                            'error'
                        )
                    }

                },
                error: function (error) {
                    console.error(error);
                }
            })
        } else if (
            /* Read more about handling dismissals below */
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire(
                'Cancelled',
                'Your imaginary file is safe :)',
                'error'
            )
        }
    })
        //
    });
})
