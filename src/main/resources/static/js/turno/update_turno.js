window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_turn');

    formulario.addEventListener('submit', function (event) {

        const formData = {
                    id: document.querySelector('#id').value,
                    odontologo_id: document.querySelector('#odontologoId').value,
                    paciente_id: document.querySelector('#pacienteId').value,
                    fecha: document.querySelector('#fecha').value,
                };
        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Turno actualizado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#id').value = "";
        document.querySelector('#odontologoId').value = "";
        document.querySelector('#pacienteId').value = "";
        document.querySelector('#fecha').value = "";

    }

    // DEJE ESTA FUNCIÓN PORQUE CREÍA QUE NO ERA NECESARIA PARA EL FUNCIONAMIENTO PERO POR ALGÚN MOTIVO
    // DEJA DE FUNCIONAR SI LA ELIMINO.
    (function(){
          let pathname = window.location.pathname;
          if (pathname == "/odontologoLista.html") {
              document.querySelector(".nav .nav-item a:last").addClass("active");
          }
        })
});