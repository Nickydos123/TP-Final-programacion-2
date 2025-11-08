package interfaces;

import modelo.Estadia;
import modelo.Hotel;

public interface IHacerCheckInyCheckOut {
   Estadia hacerCheckIn(Hotel hotel, int reservaId);
    Estadia hacerCheckOut(Hotel hotel, int estadiaId);
}
