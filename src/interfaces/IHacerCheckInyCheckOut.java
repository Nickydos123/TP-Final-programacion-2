package interfaces;

import modelo.Estadia;
import modelo.Hotel;

public interface IHacerCheckInyCheckOut {
   Estadia hacerCheckIn(int reservaId);
    Estadia hacerCheckOut(int estadiaId);
}
