package com.example.service;

import com.example.domain.Pacient;
import com.example.domain.Programare;
import com.example.domain.dto.PacientNrProg;
import com.example.domain.dto.PacientZileProg;
import com.example.repository.IRepository;
import com.example.repository.Repository;
import com.example.Exception.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServicePacient {
 private IRepository<Pacient> repo;
 private IRepository<Programare> prog_repo;

 public ServicePacient(IRepository<Pacient> repo, IRepository<Programare> repository){
     this.repo = repo;
     this.prog_repo = repository;
 }
 public void Add(int id, String nume, String prenume, int varsta) throws RepositoryException{
     if (!repo.FindById(id))
         try {
             repo.Add(new Pacient(id,nume,prenume,varsta));
         } catch (RepositoryException dublicateId) {
             throw new RuntimeException(dublicateId);
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

 }
 public void Delete(int id) throws RepositoryException{
    for(int i = 0 ; i < prog_repo.Size();i++) {
        if (prog_repo.GetAt(i).GetPacient().getId() == id) {
            prog_repo.Delete(prog_repo.GetAt(i).getId());
        }
    }
    repo.Delete(id);
 }
 public ArrayList<Pacient> GetAll() throws SQLException, RepositoryException {
     return repo.GetAll();
 }

 public void Update(int old_id,String nume,String prenume,int varsta) throws RepositoryException
 {
     if(repo.FindById(old_id))
         repo.Update(repo.GetById(old_id),new Pacient(old_id,nume,prenume,varsta));
     else
     {
         Add(old_id, nume, prenume, varsta);
         System.out.println("unfound id creat new Pacient");
     }
 }

    public ArrayList<PacientNrProg> sortbynrptog() throws SQLException, RepositoryException {
        ArrayList<PacientNrProg> list = new ArrayList<>();
        for(Pacient p : repo.GetAll()){
            list.add(new PacientNrProg(p,prog_repo.GetAll().stream().filter(x->x.GetPacient().getId()==p.getId()).toArray().length));
        }
        list.sort((x,y)->y.getNrProg()-x.getNrProg());
        return list;
    }

    //get for pacient by id the days between now and last programare DATE
    public int getDaysAfterLastProg(int id) throws SQLException, RepositoryException {
        ArrayList<Programare> programari = prog_repo.GetAll();
        int max = 0;
        for(Programare programare : programari)
            if(programare.GetPacient().getId() == id){
                if(programare.GetData().getDayOfYear() > max)
                    max = programare.GetData().getDayOfYear();
            }
        return max;
    }

    //sort pacienti by days after last programare
    public ArrayList<PacientZileProg> sortbyzileptog() throws SQLException, RepositoryException {
        ArrayList<Pacient> list = new ArrayList<>();
        for(Pacient p : repo.GetAll()){
            list.add(p);
        }
        ArrayList<PacientZileProg> list2 = new ArrayList<>();
        for(Pacient p : list){
            list2.add(new PacientZileProg(p, getDaysAfterLastProg(p.getId())));
        }
//        for(int i = 0;i<list2.size();i++)
//            System.out.println(list2.get(i).getDays());
        list2.sort((x,y)->y.getDays()-x.getDays());
        return list2;
    }
}

