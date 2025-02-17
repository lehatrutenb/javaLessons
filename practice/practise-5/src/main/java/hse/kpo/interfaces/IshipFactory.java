package hse.kpo.interfaces;


import hse.kpo.domains.Ship;

public interface IshipFactory<TParams> {
    Ship createShip(TParams shipParams, int shipNumber);
}
