package it.unisalento.pas.cityhallbe.services;

import it.unisalento.pas.cityhallbe.domains.Warning;
import it.unisalento.pas.cityhallbe.repositories.IWarningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarningService implements IWarningService{
    private final IWarningRepository warningRepository;

    @Autowired
    public WarningService(IWarningRepository warningRepository) {
        this.warningRepository = warningRepository;
    }


    @Override
    public int createWarning(Warning warning) {
        try{
            warningRepository.save(warning);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override
    public int deleteWarning(String warningID) {
        try{
            warningRepository.deleteById(warningID);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override
    public ArrayList<Warning> getAllByUser(String userId) {
        try{
            List<Warning> warningList = warningRepository.findAllByUserId(userId);
            return new ArrayList<>(warningList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
