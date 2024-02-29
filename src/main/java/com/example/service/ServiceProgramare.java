package com.example.service;

import com.example.domain.Pacient;
import com.example.domain.Programare;
import com.example.domain.dto.Programariluna;
import com.example.repository.IRepository;
import com.example.repository.Repository;
import com.example.Exception.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;

public class ServiceProgramare {
    private IRepository<Programare> programareRepository;

    public ServiceProgramare(IRepository<Programare> repository) {
        this.programareRepository = repository;
    }

    public void Add(int id, Pacient pacient, int ora, LocalDate data, String scopul_programari) throws RepositoryException {

        if (!programareRepository.FindById(id)) {
              /*
              ArrayList<Programare> prog = programareRepository.GetAll();
              for (Programare p : prog) {
                  if (p.GetOra() == ora && p.GetData().equals(data))
                      throw new RuntimeException("Ocupaied time");
              }*/
            try {
                programareRepository.Add(new Programare(id, pacient, ora, data, scopul_programari));
            } catch (RepositoryException dublicateId) {
                throw new RuntimeException(dublicateId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Programare> GetAll() throws SQLException, RepositoryException {
        return programareRepository.GetAll();
    }

    public void Delete(int id) throws RepositoryException {
        programareRepository.Delete(id);

    }

    public void Update(int old_id, Pacient pacient, int ora,LocalDate data, String scopul_programari) throws RepositoryException {
        if (!programareRepository.FindById(old_id))
            Add(old_id, pacient, ora, data, scopul_programari);
        else
            programareRepository.Update(programareRepository.GetById(old_id), new Programare(old_id, pacient, ora, data, scopul_programari));
    }

    public Pacient GetAt(int id_pacient) throws SQLException, RepositoryException {
        for (Programare prog : programareRepository.GetAll())
            if (prog.GetPacient().getId() == id_pacient)
                return prog.GetPacient();
        return null;
    }

    public int NrProgrmari(int id) {
        int nr = 0;
        for (Programare programare : programareRepository) {
            if (programare.GetPacient().getId() == id)
                nr++;
        }
        return nr;
    }

    public Dictionary<Integer, Pacient> ListaDesc() {
       Dictionary<Integer, Pacient> lista = null;
        ArrayList<Integer> integers = null;
        ArrayList<Pacient> pacients = null;
        int nr = 0;
        int max = 0;
        for (Programare programare : programareRepository)
            {
                integers.add(NrProgrmari(programare.GetPacient().GetId()));
                pacients.add(programare.GetPacient());
            }
            for (int i = 0; i < integers.size(); i++) {
                for (int integer : integers) {
                    int j = 0;
                    if (integer > max) {
                        max = integer;
                        nr = j;
                    }
                    j++;
                }
                lista.put(integers.get(nr), pacients.get(nr));
                integers.set(nr, 0);
            }

            return lista;

    }

    public ArrayList<Programariluna> countProgramariLuna() throws SQLException, RepositoryException {
        ArrayList<Programariluna> list = new ArrayList<>();
        for(int i = 1;i<=12;i++){
            int finalI = i;
            list.add(new Programariluna(i, programareRepository.GetAll().stream().filter(x->x.GetData().getMonthValue()== finalI).toArray().length));
        }
        return list;
    }
}
