Fsm - state design pattern
===

Description
---
```mermaid
stateDiagram-v2
    [*] --> Proposal
    Proposal --> Pending: waiting
    Pending --> OnGoing: serving
    OnGoing --> Finish: settle-down
    Pending --> Cancelled: cancel
    Finish --> [*]
    Cancelled --> [*]
```