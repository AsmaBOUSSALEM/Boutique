package model.shared;

/**
 * Created by asmaboussalem on 15/11/2016.
 */
public interface Entity<T>
{
    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other The other entity.
     * @return true if the identities are the same, regardles of other attributes.
     */
    boolean sameIdentityAs(T other);
}
