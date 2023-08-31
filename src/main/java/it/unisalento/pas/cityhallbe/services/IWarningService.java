package it.unisalento.pas.cityhallbe.services;

import it.unisalento.pas.cityhallbe.domains.Warning;

import java.util.ArrayList;

public interface IWarningService {
    int createWarning(Warning warning);
    int deleteWarning(String warningID);
    ArrayList<Warning> getAllByUser(String userId);
}
