window.addEventListener('load', function () {
    (function(){

      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
         for(patient of data){
            var table = document.getElementById("patientTable");
            var patientRow = table.insertRow();
            let tr_id = 'tr_' + patient.id;
            patientRow.id = tr_id;

            patientRow.innerHTML =
                    '<td class=\"td_id\">' + patient.id + '</td>' +
                    '<td class=\"td_nombre\">' + patient.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + patient.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_dni\">' + patient.dni + '</td>' +
                    '<td class=\"td_fechaIngreso\">' + patient.fechaIngreso + '</td>' +
                    '<td class=\"td_domicilioId\">' + patient.domicilio.id + '</td>' +
                    '<td class=\"td_calle\">' + patient.domicilio.calle.toUpperCase() + " " + patient.domicilio.numero + '</td>' +
                    '<td class=\"td_localidad\">' + patient.domicilio.localidad.toUpperCase() + '</td>' +
                    '<td class=\"td_provincia\">' + patient.domicilio.provincia.toUpperCase() + '</td>'
                    ;

        };

    })
    })

    // DEJE ESTA FUNCIÓN PORQUE CREÍA QUE NO ERA NECESARIA PARA EL FUNCIONAMIENTO PERO POR ALGÚN MOTIVO
    // DEJA DE FUNCIONAR SI LA ELIMINO.
    (function(){
              let pathname = window.location.pathname;
              if (pathname == "/odontologoLista.html") {
                  document.querySelector(".nav .nav-item a:last").addClass("active");
              }
            })
    })