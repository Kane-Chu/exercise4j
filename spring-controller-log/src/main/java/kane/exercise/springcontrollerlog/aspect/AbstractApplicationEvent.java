package kane.exercise.springcontrollerlog.aspect;

import org.springframework.context.ApplicationEvent;

public abstract class AbstractApplicationEvent<T> extends ApplicationEvent {

    private final Class<?> generatedBy;

    public AbstractApplicationEvent(T source, Class<?> generatedBy) {
        super(source);
        this.generatedBy = generatedBy;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getSource() {
        return (T) super.getSource();
    }

    public Class<?> getGeneratedBy() {
        return generatedBy;
    }
}
