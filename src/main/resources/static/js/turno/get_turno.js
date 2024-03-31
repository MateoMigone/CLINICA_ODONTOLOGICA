window.addEventListener('load', function () {
    (function(){

      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
         for(turn of data){
            var table = document.getElementById("turnTable");
            var turnRow =table.insertRow();
            let tr_id = 'tr_' + turn.id;
            turnRow.id = tr_id;

            turnRow.innerHTML =
                    '<td class=\"td_id\">' + turn.id + '</td>' +
                    '<td class=\"td_idOdontologo\">' + turn.odontologo.id + '</td>' +
                    '<td class=\"td_odontologo\">' + turn.odontologo.nombre.toUpperCase() + " " + turn.odontologo.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_idPaciente\">' + turn.paciente.id + '</td>' +
                    '<td class=\"td_paciente\">' + turn.paciente.nombre.toUpperCase() + " " + turn.paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_fecha\">' + turn.fecha + '</td>'
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