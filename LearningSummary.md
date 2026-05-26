---

### 2. The Learning Summary Markdown (`LEARNING_JOURNEY.md`)
Create a new file named **`LEARNING_JOURNEY.md`** and copy-paste this block. It uses clear headers, bold accents, and clean lists to make a great read for employers or your own tracking.

```markdown
# 🎓 Professional Software Engineering Learning Log

This document tracks the technical competencies, architectural insights, and core backend development skills mastered throughout the construction of the **DevBlog Live Platform**.

---

## 1. Relational Database Mapping & Architecture
* **Entity Cardinality (1-to-Many):** Gained a deep understanding of mapping real-world models into relational databases by configuring parent tables (`Users`) to seamlessly track and clean up arrays of child records (`Posts`) using `CascadeType.ALL`.
* **The Layered MVC Pattern:** Mastered architectural isolation by separating the delivery of raw JSON application feeds (`@RestController`) from the routes dedicated to rendering server-side user visual templates (`@Controller`).

## 2. Advanced Debugging & Log Diagnostics
* **Network Socket Management:** Learned to identify and work around port conflicts (e.g., standard `8080` collisions) by reconfiguring the application context parameters to use isolated environment channels like port `8081`.
* **Cyclic Evaluation Containment:** Experienced first-hand how infinite recursion triggers memory crashes (`StackOverflowError`) during object conversion, and discovered how to cleanly break serialization loops using structural annotations like `@JsonIgnore`.
* **Stack Trace Interpretation:** Developed strong debugging skills by reading detailed raw framework error dumps (such as `DataIntegrityViolationException`) to quickly find mismatched constraints between client input fields and the underlying database tables.

## 3. Full-Stack Mutation & Lifecycle Lifespans
* **Thymeleaf HTML Data Binding:** Swapped out Postman entirely by designing dynamic UI input elements. Learned how to pass live backend attributes into HTML elements, capture user form arguments, and safely forward them into application pipelines.
* **Smart Persist Lifecycle Control:** Mastered how Spring Data JPA handles database memory updates. Understood that the underlying `.save()` engine automatically runs an `INSERT` statement for fresh entities, but instantly turns into an `UPDATE` statement if a matching ID flag is already active.