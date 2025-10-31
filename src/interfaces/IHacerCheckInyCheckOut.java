package interfaces;

import modelo.Estadia;
import modelo.Hotel;

public interface IHacerCheckInyCheckOut {
   Estadia hacerCheckIn(Hotel hotel, int reservaId);
    void hacerCheckOut(Hotel hotel, int estadiaId);
}
