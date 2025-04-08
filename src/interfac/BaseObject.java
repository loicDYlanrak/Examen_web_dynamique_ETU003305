package interfac;

public abstract class BaseObject extends BaseModele {
    public abstract void save() throws Exception;
    public abstract void update() throws Exception;
    public abstract void delete() throws Exception;
}