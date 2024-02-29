package com.example.repository;
import com.example.Exception.RepositoryException;
import com.example.domain.Entitate;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
public class BinaryFileRepository<T extends Entitate> extends Repository<T> {
    private final String fileName;

    public BinaryFileRepository(String fileName) {
//        super();
        this.fileName = fileName;
        try {
            loadFile();
        } catch (IOException | ClassNotFoundException e) {
            // not very good practice
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Add(T o) throws RepositoryException, SQLException {
        super.Add(o);
        // saveFile se executa doar daca super.add() nu a aruncat exceptie
        try {
            saveFile();
        } catch (IOException e) {
            throw new RepositoryException("Error saving file " + e.getMessage(), e);
        }
    }

    @Override
    public void Delete(int id) throws RepositoryException {
        super.Delete(id);
        try{
            saveFile();
        }catch (IOException a){
            throw new RepositoryException("Error deleting entity that does not exist",a);
        }
    }

    @Override
    public void Update(T old, T newE) throws RepositoryException {
        super.Update(old, newE);
        try{
            saveFile();
        }catch (IOException a){
            throw new RepositoryException("Error deleting entity that does not exist",a);
        }
    }

    private void loadFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            this.data = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            // TODO Replace with logging
            System.out.println("Repo starting a new file");
        } finally {
            // finally se executa tot timpul
            if (ois != null)
                ois.close();
        }


    }


    private void saveFile() throws IOException {
        // try-with-resources
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
        }

//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
//
//        oos.close();
    }
}
