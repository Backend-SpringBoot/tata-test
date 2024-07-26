package tata.test.dataaccess.audit;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class AuditorAwareImpl implements AuditorAware<Integer> {

  @Override
  @NonNull
  public Optional<Integer> getCurrentAuditor() {
    return Optional.of(2);
  }
}
