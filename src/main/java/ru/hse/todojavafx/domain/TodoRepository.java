package ru.hse.todojavafx.domain;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий {@link Todo}.
 */
public interface TodoRepository {
    /**
     * Найти {@link Todo} по идентификатору.
     * Если сущность существует, то возвращает {@link Optional}, её содержащий
     *
     * @param id по которому нужно искать
     * @return {@link Optional} с найденной сущностью, иначе - пустой. Никогда не возвращает {@code null}
     */
    Optional<Todo> findById(UUID id);

    /**
     * Найти все задачи, сохраненные в данном репозитории.
     * Если сохраненных задач нет, то вернуть пустой список.
     *
     * @return список всех задач в данном репозитории
     */
    List<Todo> findAll();

    /**
     * Сохранить задачу.
     * <p>
     * Если задача - новая (т.е. {@link Todo#getId()} возвращает {@code null}), то:
     *
     * <ol>
     *     <li>Будет сгенерирован идентификатор, который будет установлен с помощью метода {@link Todo#setId(UUID)}</li>
     *     <li>Произойдет сохранение задачи</li>
     * </ol>
     *
     * В противном случае - обновление записи, соответствующей задаче с данным идентификатором.
     *
     * @param todo для сохранения или обновления
     */
    void save(Todo todo);

    /**
     * Удалить запись о задаче по её идентификатору
     *
     * @param id по которому нужно удалить задачу
     */
    void deleteById(UUID id);

    /**
     * Удалить все задачи по идентификаторам
     *
     * @param ids по которым нужно удалить задачи
     */
    void deleteByIds(Collection<UUID> ids);

    /**
     * Удалить все задачи в данном репозитории
     */
    void deleteAll();
}
